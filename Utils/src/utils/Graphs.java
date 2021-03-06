/*
 * SPDXVersion: SPDX-1.1
 * Creator: Person: Nuno Brito (nuno.brito@triplecheck.de)
 * Creator: Organization: TripleCheck (contact@triplecheck.de)
 * Created: 2013-09-13T00:00:00Z
 * LicenseName: EUPL-1.1-without-appendix
 * FileName: Graphs.java  
 * FileType: SOURCE
 * FileCopyrightText: <text> Copyright 2013 Nuno Brito, TripleCheck </text>
 * FileComment: <text> Outputs graphs as image files. </text> 
 */

package utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

public class Graphs {
    public static File generate(File baseFolder, String titles[], int[] values,
            Color backgroundColor) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // add our data values
        int i = -1;
        for(String title : titles){
           i++;
           dataset.setValue(title, values[i]);
       }
        
        
        final JFreeChart chart = 
//                ChartFactory.createPieChart("", dataset, true, true, false);
       
        ChartFactory.createPieChart("",          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);
         
        PiePlot plot = (PiePlot) chart.getPlot();
        //PiePlot3D plot = (PiePlot3D) chart.getPlot();
        //plot.setStartAngle(290);
        plot.setStartAngle(45);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        
//        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(backgroundColor);
        
//        plot.setLegendLabelGenerator(
//        new StandardPieSectionLabelGenerator("{0} {2}"));
        
        chart.setBorderVisible(false);
        chart.getPlot().setOutlineVisible(false);
        chart.getLegend().setFrame(BlockBorder.NONE);
        
        // get the same background
        chart.setBackgroundPaint(backgroundColor);
        chart.getLegend().setBackgroundPaint(backgroundColor);
        
        // hide the shadow effects
        plot.setShadowXOffset(0);
        plot.setShadowYOffset(0);
        
        //chart.getLegend().setVisible(false);
       
        plot.setCircular(true);
        //plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}", NumberFormat.getNumberInstance(), NumberFormat
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}", NumberFormat.getNumberInstance(), NumberFormat
                .getPercentInstance()));
        plot.setNoDataMessage("No data found.");
        
        Color greenColor = new Color(0x8FBC0C);
        Color redColor = new Color(0xFF0000);
        //Color redColor = new Color(0xDA6022);
        
        plot.setSectionPaint(0, greenColor);
        plot.setSectionPaint(1, redColor);
        plot.setSectionOutlinesVisible(true);
        
        
          
        final ChartRenderingInfo info = new ChartRenderingInfo(
        new StandardEntityCollection());
    final File file = new File(baseFolder, "chart.png");
        try {
            ChartUtilities.saveChartAsPNG(file, chart, 200, 160, info);
        } catch (IOException ex) {
            Logger.getLogger(Graphs.class.getName()).log(Level.SEVERE, null, ex);
        }
//        
////        final ChartPanel chartPanel = new ChartPanel(chart, true);
//       final ChartPanel chartPanel = new ChartPanel(chart, true);
//        chartPanel.setMinimumDrawWidth(0);
//        chartPanel.setMaximumDrawWidth(Integer.MAX_VALUE);
//        chartPanel.setMinimumDrawHeight(0);
//        chartPanel.setMaximumDrawHeight(Integer.MAX_VALUE);
//        JDialog dialog = new JDialog();
//        dialog.add(chartPanel);
//        dialog.setLayout(new GridLayout(1, 1));
//        dialog.setSize(400, 200);
//        dialog.setVisible(true);
        
      
       return file;
    }
   

    
}
