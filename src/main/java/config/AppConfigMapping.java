
package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "file:${user.dir}/base.properties",
        "file:${user.dir}/config/base.properties"
        })
public interface AppConfigMapping extends Config {

    @Key("base.url")
    String postgresqlHost();

    @Key("base.user1")
    String psqlUsername();

    @Key("base.password")
    String psqlPassword();


}

