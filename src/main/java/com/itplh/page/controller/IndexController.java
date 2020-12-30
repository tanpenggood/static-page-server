package com.itplh.page.controller;

import com.itplh.page.config.StaticPageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private StaticPageProperties staticPageProperties;

    @GetMapping("")
    public String index(ModelMap modelMap) {
        Map<String, List<String>> allHtmlMap = new LinkedHashMap<>();
        staticPageProperties.getResourceMappings()
                .keySet()
                .stream()
                .sorted()
                .collect(Collectors.toList())
                .forEach(virtualHost -> {
                    File file = new File(staticPageProperties.getResourceMappings().get(virtualHost));
                    if (file.exists()) {
                        List<String> htmlList = Arrays.asList(file.list((dir, name) -> name.endsWith(".html")))
                                .stream()
                                .map(name -> virtualHost + "/" + name)
                                .collect(Collectors.toList());

                        String key = virtualHost.toUpperCase().substring(0, 1);
                        List<String> cacheHtmlList = allHtmlMap.get(key);
                        if (!CollectionUtils.isEmpty(cacheHtmlList)) {
                            htmlList.addAll(cacheHtmlList);
                        }
                        log.info("{}:{} {}", virtualHost, file.getAbsolutePath(), htmlList);
                        allHtmlMap.put(key, htmlList.stream().sorted().collect(Collectors.toList()));
                    }
                });
        modelMap.put("allHtmlMap", allHtmlMap);
        return "index";
    }

}
