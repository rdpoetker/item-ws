package rdpoet.service;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class ItemsWSApplication extends ResourceConfig {

	public ItemsWSApplication() {
        packages("rdpoet.service");
        register(JacksonFeature.class);
        register(RolesAllowedDynamicFeature.class);
	}
}
