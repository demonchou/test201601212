package com.demonchou.common.component.html2image.parser.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xerces.parsers.DOMParser;
import org.cyberneko.html.HTMLConfiguration;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.demonchou.common.component.html2image.exception.ParseException;
import com.demonchou.common.component.html2image.parser.HtmlParser;

/**
 *
 * @author hzzhouhongfei
 * @version $$ HtmlParserImpl, 2017/9/29 hzzhouhongfei $$
 */
@Service
public class HtmlParserImpl implements HtmlParser
{
	private DOMParser domParser;
	private Document document;

	public HtmlParserImpl() {
		domParser = new DOMParser(new HTMLConfiguration());
		try {
			domParser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
		} catch (SAXNotRecognizedException e) {
			throw new ParseException("Can't create HtmlParserImpl", e);
		} catch (SAXNotSupportedException e) {
			throw new ParseException("Can't create HtmlParserImpl", e);
		}
	}

	@Override
	public Document getDocument()
	{
		return document;

	}
	@Override
	public void loadHtml(String html,Map<String, String> placeholder) {
		load(new StringReader(html),placeholder);
	}

	@Override
	public void load(Reader reader,Map<String, String> placeholder) {
		try {
			domParser.parse(new InputSource(reader));
			document = inject(domParser.getDocument(),placeholder);

		} catch (SAXException e) {
			throw new ParseException("SAXException while parsing HTML.", e);
		} catch (IOException e) {
			throw new ParseException("IOException while parsing HTML.", e);
		} finally {
			try {
				reader.close();
			} catch (IOException ignore) {
			}
		}
	}

	private Document inject(Document doc,Map<String, String> placeholder)
	{
		if (placeholder==null || placeholder.size()==0) return doc;
		try {
			String temp = XmlToString(doc);
			for (String key:placeholder.keySet())
			{

				temp=temp.replace("$"+key+"$", placeholder.get(key));
			}
			return stringToXml(temp);
		}
		catch (Exception ex)
		{
			return doc;
		}

	}


	private String XmlToString(Document doc) throws Exception
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		return writer.getBuffer().toString().replaceAll("\n|\r", "");
	}

	private Document stringToXml(String s) throws Exception {
		domParser.parse(s);
		return domParser.getDocument();
	}
}
