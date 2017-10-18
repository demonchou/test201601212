package com.demonchou.common.component.html2image.parser;

import java.io.Reader;
import java.util.Map;

/**
 *
 * @author hzzhouhongfei
 * @version $$ HtmlParser, 2017/9/29 hzzhouhongfei $$
 */
public interface HtmlParser extends DocumentHolder
{
	void loadHtml(String html,Map<String, String> placeholder);

	void load(Reader reader,Map<String, String> placeholder);

}
