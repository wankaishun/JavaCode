package com.liqun.web;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.ext.jsp.TaglibFactory;

/**
 * 
 * 
 * @author Michelangelo
 *
 */
public class DefaultFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	public TaglibFactory getTaglibFactory() {

		// 如果使用默认的FreeMarkerConfigurer，项目运行期间控制台会有警告：
		// Custom EL functions won't be loaded because no ObjectWarpper was specified
		// 加入以下代码，避免加载页面时出现以上警告。
		TaglibFactory tagLibFactory = super.getTaglibFactory();
		if (tagLibFactory.getObjectWrapper() == null) {
			tagLibFactory.setObjectWrapper(super.getConfiguration().getObjectWrapper());
		}
		return tagLibFactory;
	}
}
