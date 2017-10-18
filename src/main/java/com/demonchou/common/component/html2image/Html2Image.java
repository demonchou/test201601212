package com.demonchou.common.component.html2image;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.demonchou.common.component.html2image.parser.HtmlParser;
import com.demonchou.common.component.html2image.parser.impl.HtmlParserImpl;
import com.demonchou.common.component.html2image.renderer.ImageRenderer;
import com.demonchou.common.component.html2image.renderer.impl.ImageRendererImpl;

/**
 *
 * @author hzzhouhongfei
 * @version $$ Html2image, 2017/9/29 hzzhouhongfei $$
 */
public class Html2Image
{
	private HtmlParser parser = new HtmlParserImpl();

	private ImageRenderer imageRenderer;

	public static Html2Image fromHtml(String html,Map<String, String> placeholder) {
		final Html2Image html2Image = new Html2Image();
		html2Image.getParser().loadHtml(html,placeholder);
		return html2Image;
	}

	public HtmlParser getParser() {
		return parser;
	}

	public ImageRenderer getImageRenderer() {
		if (imageRenderer == null) {
			imageRenderer = new ImageRendererImpl(parser);
		}
		return imageRenderer;
	}
}
