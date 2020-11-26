
package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "file:${user.dir}/base.properties",
        "file:${user.dir}/config/base.properties",
        "classpath:base.properties"
        })
public interface ConfigMapping extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("base.user1")
    String username();

    @Key("base.password")
    String password();

    @Key("selenide.browser")
    @DefaultValue("chrome")
    String selenideBrowser();

    @Key("is.remote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remote.driver.url")
    String remoteDriverUrl();

    @Key("suppress.known.issues")
    @DefaultValue("false")
    boolean isSuppressKnownIssues();



}

