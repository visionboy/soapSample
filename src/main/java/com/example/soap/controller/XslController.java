package com.example.soap.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StreamUtils;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;


@RestController
public class XslController {

    @GetMapping(value = "/transform/test", produces = "text/html;charset=UTF-8")
    public String transformPredefined() {
        try {
            // 1. 리소스 폴더의 XML과 XSL 소스 정의
            InputStream xmlInput = new ClassPathResource("xml/data.xml").getInputStream();
            InputStream xslInput = new ClassPathResource("xml/style.xsl").getInputStream();

            // 2. String으로 변환
//            String xmlContent = StreamUtils.copyToString(xmlInput, StandardCharsets.UTF_8);

            Source xmlSource = new StreamSource(xmlInput);
            Source xslSource = new StreamSource(xslInput);

            // 2. 출력 대상 정의 (메모리 버퍼)
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Result outputTarget = new StreamResult(outputStream);

            // 3. 변환기 생성 및 실행
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslSource);
            transformer.transform(xmlSource, outputTarget);

            return outputStream.toString(StandardCharsets.UTF_8.name());

        } catch (Exception e) {
            return "Error during transformation: " + e.getMessage();
        }
    }

    @PostMapping(value = "/transform", consumes = "application/xml", produces = "text/html;charset=UTF-8")
    public String transformDynamic(@RequestBody String xmlInput) {
        try {
            // 전달받은 XML 데이터를 소스로 사용
            Source xmlSource = new StreamSource(new StringReader(xmlInput));
            
            // XSL 스타일은 고정된 것을 사용 (필요시 XSL도 전달받도록 변경 가능)
            InputStream xslInput = new ClassPathResource("xml/style.xsl").getInputStream();
            Source xslSource = new StreamSource(xslInput);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Result outputTarget = new StreamResult(outputStream);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xslSource);
            transformer.transform(xmlSource, outputTarget);

            return outputStream.toString(StandardCharsets.UTF_8.name());

        } catch (Exception e) {
            return "Error during transformation: " + e.getMessage();
        }
    }
}
