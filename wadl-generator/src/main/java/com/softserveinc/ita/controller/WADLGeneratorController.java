package com.softserveinc.ita.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jvnet.ws.wadl.Application;
import org.jvnet.ws.wadl.Doc;
import org.jvnet.ws.wadl.Param;
import org.jvnet.ws.wadl.ParamStyle;
import org.jvnet.ws.wadl.Representation;
import org.jvnet.ws.wadl.Request;
import org.jvnet.ws.wadl.Resource;
import org.jvnet.ws.wadl.Resources;
import org.jvnet.ws.wadl.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("/application.wadl")
public class WADLGeneratorController {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @RequestMapping(method=RequestMethod.GET, produces={"application/xml"} )
    public @ResponseBody Application generateWadl(HttpServletRequest request) {
        Application result = new Application();
        Doc doc = new Doc();
        doc.setTitle("Spring REST Service WADL");
        result.getDoc().add(doc);
        Resources wadResources = new Resources();
        wadResources.setBase(getBaseUrl(request));

        Map<RequestMappingInfo, HandlerMethod> handletMethods = handlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handletMethods.entrySet()) {
            Resource wadlResource = new Resource();

            HandlerMethod handlerMethod = entry.getValue();
            RequestMappingInfo mappingInfo = entry.getKey();

            Set<String> pattern =  mappingInfo.getPatternsCondition().getPatterns();
            Set<RequestMethod> httpMethods =  mappingInfo.getMethodsCondition().getMethods();
            ProducesRequestCondition producesRequestCondition = mappingInfo.getProducesCondition();
            Set<MediaType> mediaTypes = producesRequestCondition.getProducibleMediaTypes();

            for (RequestMethod httpMethod : httpMethods) {
                org.jvnet.ws.wadl.Method wadlMethod = new org.jvnet.ws.wadl.Method();

                for (String uri : pattern) {
                    wadlResource.setPath(uri);
                }

                wadlMethod.setName(httpMethod.name());
                Method javaMethod = handlerMethod.getMethod();
                wadlMethod.setId(javaMethod.getName());
                Doc wadlDocMethod = new Doc();
                wadlDocMethod.setTitle(javaMethod.getDeclaringClass().getName()+"."+javaMethod.getName());
                wadlMethod.getDoc().add(wadlDocMethod);

                // Request
                Request wadlRequest = new Request();

                Annotation[][] annotations = javaMethod.getParameterAnnotations();
                for (Annotation[] annotation : annotations) {
                    for (Annotation annotation2 : annotation) {
                        if (annotation2 instanceof RequestParam ) {
                            RequestParam param2 = (RequestParam)annotation2;
                            Param waldParam = new Param();
                            waldParam.setName(param2.value());
                            waldParam.setStyle(ParamStyle.QUERY);
                            waldParam.setRequired(param2.required());
                            String defaultValue = cleanDefault(param2.defaultValue());
                            if ( !defaultValue.equals("") ) {
                                waldParam.setDefault(defaultValue);
                            }
                            wadlRequest.getParam().add(waldParam);
                        } else if ( annotation2 instanceof PathVariable ) {
                            PathVariable param2 = (PathVariable)annotation2;
                            Param waldParam = new Param();
                            waldParam.setName(param2.value());
                            waldParam.setStyle(ParamStyle.TEMPLATE);
                            waldParam.setRequired(true);
                            wadlRequest.getParam().add(waldParam);
                        }
                    }
                }
                if ( ! wadlRequest.getParam().isEmpty() ) {
                    wadlMethod.setRequest(wadlRequest);
                }

                // Response
                if ( !mediaTypes.isEmpty() ) {
                    Response wadlResponse = new Response();
                    wadlResponse.getStatus().add(200l);
                    for (MediaType mediaType : mediaTypes) {
                        Representation wadlRepresentation = new Representation();
                        wadlRepresentation.setMediaType(mediaType.toString());
                        wadlResponse.getRepresentation().add(wadlRepresentation);
                    }
                    wadlMethod.getResponse().add(wadlResponse);
                }

                wadlResource.getMethodOrResource().add(wadlMethod);

            }

            wadResources.getResource().add(wadlResource);

        }
        result.getResources().add(wadResources);

        return result;
    }


    private String getBaseUrl (HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        return request.getScheme()+"://"+ request.getServerName()+":"+ request.getServerPort() + requestUri;
    }

    private String cleanDefault(String value) {
        value = value.replaceAll("\t", "");
        value = value.replaceAll("\n", "");
        return value;
    }
}
