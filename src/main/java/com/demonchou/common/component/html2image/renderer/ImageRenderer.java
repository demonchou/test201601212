package com.demonchou.common.component.html2image.renderer;

import java.io.File;

/**
 *
 * @author hzzhouhongfei
 * @version $$ ImageRenderer, 2017/9/29 hzzhouhongfei $$
 */
public interface ImageRenderer extends LayoutHolder
{
	void saveImage(String filename);

	void saveImage(File file);
}
