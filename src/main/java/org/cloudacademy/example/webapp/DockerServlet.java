package org.cloudacademy.example.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.model.Container;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collections;

@WebServlet(name = "DockerServlet", urlPatterns = { "/home", "/action1", "/action2", "/action3" }, loadOnStartup = 1)
public class DockerServlet extends HttpServlet {
    final static String CONTAINER_NETWORK_NAME = System.getenv("CONTAINER_NETWORK");
    final static String CONTAINER_SOCAT_ENABLED = System.getenv("CONTAINER_SOCAT_ENABLED");

    // final static Log logger = LogFactory.getLog(DockerServlet.class);

    private static Logger logger = LogManager.getLogger(DockerServlet.class);
    // final static Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
    // logger.debug("Debug message");

    private static DockerClientConfig config = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String containerId = null;
        String containerIp = null;

        try {
            if(config == null){
                if ((CONTAINER_SOCAT_ENABLED != null) && (CONTAINER_SOCAT_ENABLED.equals("true"))){
                    //using socat to connect to docker api server:
                    //docker run --net network123 -e CONTAINER_NETWORK=network123 -e CONTAINER_SOCAT_ENABLED=true --name webapp --rm -p 8000:8080 cloudacademydevops/webapp:latest
                    //needs to be used on macos as /run/docker.sock is not exposed
                    config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("tcp://socat:2375").build();
                }
                else{
                    //using unix socket /run/docker.sock:
                    //docker run -v /run/docker.sock:/run/docker.sock --net network123 -e CONTAINER_NETWORK=network123 --name webapp --rm -p 8000:8080 cloudacademydevops/webapp:latest
                    config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
                }
            }

            // get the docker client
            DockerClient client = DockerClientBuilder.getInstance(config).build();
            // prepare command to retrieve the list of (running) containers

            ListContainersCmd listContainersCmd = client.listContainersCmd().withStatusFilter(Collections.singleton("running"));

            // and set the generic filter regarding name
            listContainersCmd.getFilters().put("name", Arrays.asList("webapp"));
            // finally, run the command
            List<Container> containerList = listContainersCmd.exec();

            Iterator<Container> containerIterator = containerList.iterator();
            while (containerIterator.hasNext()) {
                Container container = containerIterator.next();
    
                containerId = container.getId().substring(0, 10);
                containerIp = container.getNetworkSettings().getNetworks().get(CONTAINER_NETWORK_NAME).getIpAddress();
            }
        }
        catch (Exception e){
            logger.error("docker problem:" + e.getMessage());
            containerId = "unknown";
            containerIp = "unknown";
        }

        request.setAttribute("containerid", containerId);
        request.setAttribute("containerip", containerIp);

        String path = request.getServletPath() != null ? request.getServletPath() : "";
        switch (path) {
        case "/home":
            request.getRequestDispatcher("response.jsp").forward(request, response);
            break;
        case "/action1":
            logger.debug("action 1 called...");
            response.getWriter().println("action->1");
            response.getWriter().println(containerId);
            response.getWriter().println(containerIp);
            break;
        case "/action2":
            logger.debug("action 2 called...");
            response.getWriter().println("action->2");
            response.getWriter().println(containerId);
            response.getWriter().println(containerIp);
            break;
        case "/action3":
            logger.debug("action 3 called...");
            response.getWriter().println("action->3");
            response.getWriter().println(containerId);
            response.getWriter().println(containerIp);
            break;
        }
    }
}