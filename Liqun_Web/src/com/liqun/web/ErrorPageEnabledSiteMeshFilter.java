package com.liqun.web;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.webapp.contentfilter.BasicSelector;

/**
 * 
 * 发现在返回400响应代码的freemarker结果时，sitemesh会罢工，但是若果是使用400响应代码的jsp结果，sitemesh则会正常工作。当前sitemesh版本为3.0.1。<br>
 * 
 * 所以这里暂且让includeErrorPages=true，不知以后sitemesh是否会修复该问题。
 * 
 * <a href=
 *      "https://stackoverflow.com/questions/15354895/generic-error-page-not-decorated">spring
 *      - Generic Error Page not decorated - Stack Overflow</a>
 * @author Michelangelo
 *
 */
public class ErrorPageEnabledSiteMeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.setCustomSelector(
				new BasicSelector(true, "text/html", "application/xhtml+xml", "application/vnd.wap.xhtml+xml"));
	}
}
