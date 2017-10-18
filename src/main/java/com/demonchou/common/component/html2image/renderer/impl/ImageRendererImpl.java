package com.demonchou.common.component.html2image.renderer.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageWriteParam;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xhtmlrenderer.render.Box;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import com.demonchou.common.component.html2image.exception.RenderException;
import com.demonchou.common.component.html2image.parser.DocumentHolder;
import com.demonchou.common.component.html2image.renderer.ImageRenderer;
import com.demonchou.common.component.html2image.util.FormatNameUtil;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ImageRendererImpl, 2017/9/29 hzzhouhongfei $$
 */
@Service
public class ImageRendererImpl implements ImageRenderer
{
	public static final int DEFAULT_WIDTH = 1024;
	public static final int DEFAULT_HEIGHT = 768;

	private DocumentHolder documentHolder;

	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT;
	private boolean autoHeight = true;

	private String imageFormat = null;
	private float writeCompressionQuality = 10.0f;
	private int writeCompressionMode = ImageWriteParam.MODE_COPY_FROM_METADATA;
	private String writeCompressionType = null;
	private Box rootBox;

	private BufferedImage bufferedImage;
	private int cacheImageType = -1;
	private Document cacheDocument;

	@Override
	public Box getRootBox() {
		if (rootBox == null) {
			getBufferedImage();
		}
		return rootBox;
	}

	public ImageRendererImpl(DocumentHolder documentHolder) {
		this.documentHolder = documentHolder;
	}

	public BufferedImage getBufferedImage() {
		return getBufferedImage(BufferedImage.TYPE_INT_ARGB);
	}

	public BufferedImage getBufferedImage(int imageType) {
		final Document document = documentHolder.getDocument();
		if (bufferedImage != null || cacheImageType != imageType || cacheDocument != document) {
			cacheImageType = imageType;
			cacheDocument = document;

			Graphics2DRenderer renderer = new Graphics2DRenderer();
			renderer.setDocument(document, document.getDocumentURI());
			Dimension dimension = new Dimension(width, height);
			bufferedImage = new BufferedImage(width, height, imageType);

			if (autoHeight) {
				// do layout with temp buffer
				Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
				renderer.layout(graphics2D, new Dimension(width, height));
				graphics2D.dispose();

				Rectangle size = renderer.getMinimumSize();
				final int autoWidth = (int) size.getWidth();
				final int autoHeight = (int) size.getHeight();
				bufferedImage = new BufferedImage(autoWidth, autoHeight, imageType);
				dimension = new Dimension(autoWidth, autoHeight);
			}

			Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
			renderer.layout(graphics2D, dimension);
			renderer.render(graphics2D);
			rootBox = renderer.getPanel().getRootBox();
			graphics2D.dispose();
		}
		return bufferedImage;
	}

	@Override
	public void saveImage(String filename)
	{
		long startTime = System.currentTimeMillis();
		saveImage(new File(filename));
		long endTime = System.currentTimeMillis();
		System.out.println("========生成文件时间：" + (endTime - startTime));
	}

	@Override
	public void saveImage(File file)
	{
		try {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			save(outputStream, file.getName(), true);
		} catch (IOException e) {
			throw new RenderException("IOException while rendering image to " + file.getAbsolutePath(), e);
		}
	}

	private void save(OutputStream outputStream, String filename, boolean closeStream) {
		try {
			final String imageFormat = getImageFormat(filename);
			final FSImageWriter imageWriter = getImageWriter(imageFormat);
			final boolean isBMP = "bmp".equalsIgnoreCase(imageFormat);
			final BufferedImage bufferedImage = getBufferedImage(isBMP ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
			long startTime = System.currentTimeMillis();
			imageWriter.write(bufferedImage, outputStream);
			long endTime = System.currentTimeMillis();
			System.out.println("========>写文件时间：" + (endTime - startTime));
		} catch (IOException e) {
			throw new RenderException("IOException while rendering image", e);
		} finally {
			if (closeStream) {
				try {
					outputStream.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	private String getImageFormat(String filename) {
		if (this.imageFormat != null) {
			return imageFormat;
		}
		if (filename != null) {
			return FormatNameUtil.formatForFilename(filename);
		}
		return FormatNameUtil.getDefaultFormat();
	}

	private FSImageWriter getImageWriter(String imageFormat) {
		FSImageWriter imageWriter = new FSImageWriter(imageFormat);
		imageWriter.setWriteCompressionMode(writeCompressionMode);
		imageWriter.setWriteCompressionQuality(writeCompressionQuality);
		imageWriter.setWriteCompressionType(writeCompressionType);
		return imageWriter;
	}
}
