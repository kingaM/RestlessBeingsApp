package com.comp1008.MrugalaBartlett.FieldKnow.Statistics;

import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;

public class Labels {

	private CategorySeries series;
	private DefaultRenderer renderer;

	public Labels(CategorySeries series, DefaultRenderer renderer) {
		super();
		this.series = series;
		this.renderer = renderer;
	}

	public DefaultRenderer getRenderer() {
		return renderer;
	}

	public CategorySeries getSeries() {
		return series;
	}

	public void setRenderer(DefaultRenderer renderer) {
		this.renderer = renderer;
	}

	public void setSeries(CategorySeries series) {
		this.series = series;
	}
}
