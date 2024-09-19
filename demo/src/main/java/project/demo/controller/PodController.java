package project.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

@RestController
@RequestMapping("/api")
public class PodController {

    @GetMapping("/getRemoveScriptIP")
    public String getRemoveScriptIP() {
        try (KubernetesClient client = new DefaultKubernetesClient()) {
            List<Pod> pods = client.pods().inNamespace("default").withLabel("app", "remove-script").list().getItems();
            if (!pods.isEmpty()) {
                String podIP = pods.get(0).getStatus().getPodIP();
                return podIP;
            } else {
                return "Pod not found";
            }
        } catch (Exception e) {
            return "Message String = " + e.getMessage(); // "Failure executing"
            //return "Error: Unable to fetch pod IP address";
        }
    }
}
