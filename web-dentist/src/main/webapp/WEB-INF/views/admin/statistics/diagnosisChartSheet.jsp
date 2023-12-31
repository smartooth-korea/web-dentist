<!-- Styles -->
<style>
#chartdiv {
	width: 100%;
	height: 500px;
}
</style>

<!-- Resources -->
<script src="/amchart/index.js"></script>
<script src="/amchart/xy.js"></script>
<script src="/amchart/themes/Animated.js"></script>

<!-- Chart code -->
<script>
am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");

// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
var chart = root.container.children.push(
  am5xy.XYChart.new(root, {
    panX: false,
    panY: false,
    wheelX: "panX",
    wheelY: "zoomX",
    layout: root.verticalLayout,
    arrangeTooltips: false
  })
);

// Use only absolute numbers
chart.getNumberFormatter().set("numberFormat", "#.#s");

// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);

// Data
var data = [
  {
    age: "85+",
    male: -0.1,
    female: 0.3
  },
  {
    age: "80-54",
    male: -0.2,
    female: 0.3
  },
  {
    age: "75-79",
    male: -0.3,
    female: 0.6
  },
  {
    age: "70-74",
    male: -0.5,
    female: 0.8
  },
  {
    age: "65-69",
    male: -0.8,
    female: 1.0
  },
  {
    age: "60-64",
    male: -1.1,
    female: 1.3
  },
  {
    age: "55-59",
    male: -1.7,
    female: 1.9
  },
  {
    age: "50-54",
    male: -2.2,
    female: 2.5
  },
  {
    age: "45-49",
    male: -2.8,
    female: 3.0
  },
  {
    age: "40-44",
    male: -3.4,
    female: 3.6
  },
  {
    age: "35-39",
    male: -4.2,
    female: 4.1
  },
  {
    age: "30-34",
    male: -5.2,
    female: 4.8
  },
  {
    age: "25-29",
    male: -5.6,
    female: 5.1
  },
  {
    age: "20-24",
    male: -5.1,
    female: 5.1
  },
  {
    age: "15-19",
    male: -3.8,
    female: 3.8
  },
  {
    age: "10-14",
    male: -3.2,
    female: 3.4
  },
  {
    age: "5-9",
    male: -4.4,
    female: 4.1
  },
  {
    age: "0-4",
    male: -5.0,
    female: 4.8
  }
];

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var yAxis = chart.yAxes.push(
  am5xy.CategoryAxis.new(root, {
    categoryField: "age",
    renderer: am5xy.AxisRendererY.new(root, {
      inversed: true,
      cellStartLocation: 0.1,
      cellEndLocation: 0.9
    })
  })
);

yAxis.data.setAll(data);

var xAxis = chart.xAxes.push(
  am5xy.ValueAxis.new(root, {
    renderer: am5xy.AxisRendererX.new(root, {})
  })
);

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function createSeries(field, labelCenterX, pointerOrientation, rangeValue) {
  var series = chart.series.push(
    am5xy.ColumnSeries.new(root, {
      xAxis: xAxis,
      yAxis: yAxis,
      valueXField: field,
      categoryYField: "age",
      sequencedInterpolation: true,
      clustered: false,
      tooltip: am5.Tooltip.new(root, {
        pointerOrientation: pointerOrientation,
        labelText: "{categoryY}: {valueX}"
      })
    })
  );

  series.columns.template.setAll({
    height: am5.p100
  });

  series.bullets.push(function() {
    return am5.Bullet.new(root, {
      locationX: 1,
      locationY: 0.5,
      sprite: am5.Label.new(root, {
        centerY: am5.p50,
        text: "{valueX}",
        populateText: true,
        centerX: labelCenterX
      })
    });
  });

  series.data.setAll(data);
  series.appear();

  var rangeDataItem = xAxis.makeDataItem({
    value: rangeValue
  });
  xAxis.createAxisRange(rangeDataItem);
  rangeDataItem.get("grid").setAll({
    strokeOpacity: 1,
    stroke: series.get("stroke")
  });

  var label = rangeDataItem.get("label");
  label.setAll({
    text: field.toUpperCase(),
    fontSize: "1.1em",
    fill: series.get("stroke"),
    paddingTop: 10,
    isMeasured: false,
    centerX: labelCenterX
  });
  label.adapters.add("dy", function() {
    return -chart.plotContainer.height();
  });

  return series;
}

createSeries("male", am5.p100, "right", -3);
createSeries("female", 0, "left", 4);

// Add cursor
// https://www.amcharts.com/docs/v5/charts/xy-chart/cursor/
var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);

// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(1000, 100);

}); // end am5.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>