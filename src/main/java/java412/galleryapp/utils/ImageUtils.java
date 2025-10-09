package java412.galleryapp.utils;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageUtils {

    private static final double MIN_HEIGHT = 180.0;

    public static byte[] resizeImage(byte[] data) throws IOException {

        String formatName = getImageFormat(data);

        if (formatName == null) {
            throw new IOException("Невозможно определить формат изображения");
        }

        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(data));

        if (originalImage == null) {
            throw new IOException("Невозможно прочитать изображение из byte[]");
        }

        double scale = MIN_HEIGHT / originalImage.getHeight();

        BufferedImage resizedImage = Thumbnails.of(originalImage)
                .scale(scale)
                .asBufferedImage();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        if(formatName.equalsIgnoreCase("jpeg")) {
            formatName = "jpg";
        }

        ImageIO.write(resizedImage, formatName, baos);

        return baos.toByteArray();

    }

    public static String getImageFormat(byte[] data) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ImageInputStream iis = ImageIO.createImageInputStream(bais)) {

            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

            if (!readers.hasNext()) {
                return null;
            }

            ImageReader reader = readers.next();

            return reader.getFormatName().toLowerCase();

        }
    }

}
