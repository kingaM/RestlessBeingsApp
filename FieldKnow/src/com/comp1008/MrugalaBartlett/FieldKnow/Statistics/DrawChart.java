/**
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 * Based on AChartEngine Library
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Statistics;

import java.util.ArrayList;

import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.graphics.Color;

public class DrawChart {

	public static Labels pieChart(ArrayList<PieChartValues> values) {

		CategorySeries series = new CategorySeries("Pie Chart");
		series.clear();
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsColor(Color.BLACK);

		for (PieChartValues value : values) {
			series.add(value.getLabel(), value.getValue());
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(value.getColor());
			renderer.addSeriesRenderer(r);
		}
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		renderer.setChartTitleTextSize(20);
		Labels labels = new Labels(series, renderer);
		return labels;
	}

}
