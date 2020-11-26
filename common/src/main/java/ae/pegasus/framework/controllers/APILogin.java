package ae.pegasus.framework.controllers;

import ae.pegasus.framework.app_context.AppContext;
import ae.pegasus.framework.http.G4HttpClient;
import ae.pegasus.framework.http.OperationResult;
import ae.pegasus.framework.model.*;
import ae.pegasus.framework.model.entities.Entities;
import ae.pegasus.framework.services.*;
import ae.pegasus.framework.utils.StringUtils;
import org.apache.log4j.Logger;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Given;
import org.junit.Assert;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class APILogin {

    private static AuthService signService = new AuthService();
    private static UserService userService = new UserService();
    private static AppContext context = AppContext.get();
    private static Logger log = Logger.getLogger(APILogin.class);
    private static final String ADMIN_ROLE = "admin";

    public static User getUserByRole(String role) {
        User searchedUser = null;
        for (User user : Entities.getUsers()) {
            if (user.getRoles() != null && user.getRoles().contains(role.toUpperCase())) {
                searchedUser = user;
                break;
            }
        }
        return searchedUser;
    }

    /**
     * Sign in as user and add LoggedUser in AppContext.
     *
     * @param role predefined Role of User for sign in.
     */
    public OperationResult<Token> signInAsUser(String role) {
        User user = getUserByRole(role);
        return signInAsUser(user);
    }

    /**
     * Sign in as user and add LoggedUser in AppContext.
     *
     * @param user User for sign in.
     */
    public OperationResult<Token> signInAsUser(User user) {
        log.info("Signing in as user: " + user);

        OperationResult<Token> operationResult = signService.signIn(user.getName(), user.getPassword());
        if (operationResult.isSuccess()) {
            Token token = operationResult.getEntity();
            context.setLoggedUser(new LoggedUser(user));
            G4HttpClient.setCookie(Token.tokenCookieProperty, token.getValue());

            //update user
            OperationResult<User> meResult = new UserService().me();
            User me = meResult.getEntity();
            me.setPassword(user.getPassword());

            context.setLoggedUser(new LoggedUser(me));
        }
        return operationResult;
    }

    public OperationResult<Token> signInAsUser(String UserName , String Password) {
        log.info("Signing in as user: " + UserName);

        OperationResult<Token> operationResult = signService.signIn(UserName, Password);
        if (operationResult.isSuccess()) {
            Token token = operationResult.getEntity();
            //  context.setLoggedUser(new LoggedUser(UserName , Password));
            G4HttpClient.setCookie(Token.tokenCookieProperty, token.getValue());

            //update user
            OperationResult<User> meResult = new UserService().me();
            User me = meResult.getEntity();
            me.setPassword(Password);

            context.setLoggedUser(new LoggedUser(me));
        }
        return operationResult;
    }

    /**
     * Sign out as user
     *
     * @return {@link OperationResult<RequestResult>}
     */
    public OperationResult<RequestResult> signOut() {
        log.info("Signing out");

        OperationResult<RequestResult> operationResult = signService.signOut();
        if (operationResult.isSuccess()) {
            context.setLoggedUser(null);
        }

        return operationResult;
    }

    @Given("I sign in as user with permissions $permissions")
    public void signInWithPermissions(String permString) {
        signInAsUser(ADMIN_ROLE);

        User user = userService.getOrCreateUserWithPermissions(permString);
        Assert.assertNotNull(user);

        signInAsUser(user);
    }

    @Given("Set new permissions: $permissions to current user and relogin")
    public void setNewPermissionToCurrentUserAndRelogin(String permString) {
        List<String> permissions = StringUtils.splitToList(permString);

        User currentUser = AppContext.get().getLoggedUser().getUser();
        signInAsUser(ADMIN_ROLE);
        userService.setPermissions(currentUser, permissions);
        signInAsUser(currentUser);
    }

    @Given("I sign in as user with classifications: $value")
    public void singinAsUserWithClassification(String value) {
        String[] classifications = StringUtils.splitToArray(value);

        User user = User.newBuilder()
                .randomUser()
                .withClassification(classifications)
                .withAllPermission()
                .withAllSources()
                .build();

        User createdUser = userService.createUserWithPermissions(user);
        signInAsUser(createdUser);
    }


    @Given("I sign in as user with orgUnits: $orgUnitsString")
    public void signInAsUserWithOrgUnits(String orgUnitsString) {
        String[] orgUnits = StringUtils.splitToArray(orgUnitsString);

        User user = User.newBuilder()
                .randomUser()
                .withAllClassification()
                .withAllPermission()
                .withAllSources()
                .build();

        OrganizationService organizationService = new OrganizationService();
        for (String orgUnit : orgUnits) {
            Team team = organizationService.getOrCreateTeamByName(orgUnit);
            userService.addOrgUnit(user, team.getId());
        }

        User createdUser = userService.createUserWithPermissions(user);
        signInAsUser(createdUser);
    }


    @AfterStories
    public void afterStoryTearDown() {
        log.info("TearDown for temp users, titles & responsibilities");
        assertTrue("Unable login as admin user!", signInAsUser(ADMIN_ROLE).isSuccess());

        List<OperationResult> operationResults;

        operationResults = new UserService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete User. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }

        operationResults = new TitleService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete Title. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }

        operationResults = new ResponsibilityService().removeAll();
        for (OperationResult result : operationResults) {
            if (!result.isSuccess()) {
                log.error("Unable to delete Responsibility. Code:" + result.getCode() + ", Response:" + result.getMessage());
            }
        }
    }
}
