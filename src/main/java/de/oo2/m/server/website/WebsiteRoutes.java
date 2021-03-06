package de.oo2.m.server.website;

import de.oo2.m.server.ServerContext;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * This class adds the route for the website.
 */
public class WebsiteRoutes {
    private ServerContext serverContext;
    private Map<String, Object> model = null;

    /**
     * Constructor.
     *
     * @param serverContext the server context
     */
    public WebsiteRoutes(ServerContext serverContext) {
        this.serverContext = serverContext;

        // Freemarker configuration
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setClassForTemplateLoading(configuration.getClass(), "/public/");

        get("/", (request, response) -> {

            return new ModelAndView(getModel(), "app.html");

        }, new FreeMarkerEngine(configuration));

    }

    /**
     * Returns the model to be mapped in the view by freemarker.
     *
     * @return the model
     */
    private Map<String, Object> getModel() {
        if (model == null) {
            model = initializeModel();
        }
        model = updateModel(model);
        return model;
    }

    /**
     * Initializes the model.
     *
     * @return the model
     */
    private Map<String, Object> initializeModel() {
        Map<String, Object> model = new HashMap<>();

        model.put("dataSource", "server");
        model.put("version", serverContext.getVersion());
        model.put("hostname", serverContext.getHostname());

        return model;
    }

    /**
     * Updates the values in the model.
     *
     * @param model the model to be updated
     * @return the updated model
     */
    private Map<String, Object> updateModel(Map<String, Object> model) {

        model.put("copyrightYear", Integer.toString(LocalDate.now().getYear()));

        return model;
    }
}
