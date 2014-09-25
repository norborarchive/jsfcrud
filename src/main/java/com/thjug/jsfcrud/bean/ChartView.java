package com.thjug.jsfcrud.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.DonutChartModel;

/**
 *
 * @author nuboat
 */
@ManagedBean
public class ChartView implements Serializable {

	private DonutChartModel donutModel;

	@PostConstruct
	public void init() {
		createDonutModels();
	}

	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	private void createDonutModels() {
		donutModel = initDonutModel();
		donutModel.setTitle("Custom Options");
		donutModel.setLegendPosition("e");
		donutModel.setSliceMargin(5);
		donutModel.setShowDataLabels(true);
		donutModel.setDataFormat("value");
		donutModel.setShadow(false);
	}

	private DonutChartModel initDonutModel() {
		final DonutChartModel model = new DonutChartModel();

		final Map<String, Number> circle1 = new LinkedHashMap<>();
		circle1.put("Brand 1", 150);
		circle1.put("Brand 2", 400);
		circle1.put("Brand 3", 200);
		circle1.put("Brand 4", 10);
		model.addCircle(circle1);

		final Map<String, Number> circle2 = new LinkedHashMap<>();
		circle2.put("Brand 1", 540);
		circle2.put("Brand 2", 125);
		circle2.put("Brand 3", 702);
		circle2.put("Brand 4", 421);
		model.addCircle(circle2);

		final Map<String, Number> circle3 = new LinkedHashMap<>();
		circle3.put("Brand 1", 40);
		circle3.put("Brand 2", 325);
		circle3.put("Brand 3", 402);
		circle3.put("Brand 4", 421);
		model.addCircle(circle3);

		return model;
	}
}
