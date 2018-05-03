package com.github.binarywang.utils.qrcode;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Qrcode {

    private static Logger logger               = LoggerFactory.getLogger(QrcodeUtils.class);

    private String        content              = "https://www.baidu.com/";

    private List<Path>    generatedQrcodePaths = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Qrcode qrcode = new Qrcode();
        qrcode.setup();
        qrcode.createQrcode();
        qrcode.decodeQrcode();
    }

    public void setup() {
        BasicConfigurator.configure();
    }

    /**
     * 生成图形二维码
     */
    public void createQrcode() throws Exception {
        String pathPrefix = "/Users/onlyone/open-github/qrcode-utils/src/main/resources/qrcode/";
        byte[] bytes = QrcodeUtils.createQrcode(content, 800, null);
        Path p = Paths.get(pathPrefix + "qrcode_800_1.png");
        generatedQrcodePaths.add(p);
        Files.write(p, bytes);
    }

    /**
     * 创建带logo的图形二维码
     */
    public void createQrcodeWithLogo() throws Exception {
        String path = "/Users/onlyone/open-github/qrcode-utils/src/main/resources/qrcode_logo/";
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("logo.png");
        File logoFile = new File(path + "1.jpg");
        FileUtils.copyInputStreamToFile(inputStream, logoFile);
        logger.info("{}", logoFile);

        // 生成二维码字节数组
        byte[] bytes = QrcodeUtils.createQrcode(content, 800, logoFile);
        // 生成图片
        Files.write(Paths.get(path + "3.png"), bytes);
    }

    /**
     * 解析二维码内容
     */
    public void decodeQrcode() throws Exception {
        System.out.println("解析二维码内容:");
        for (Path path : generatedQrcodePaths) {
            System.out.println(QrcodeUtils.decodeQrcode(path.toFile()));
        }
    }

}
