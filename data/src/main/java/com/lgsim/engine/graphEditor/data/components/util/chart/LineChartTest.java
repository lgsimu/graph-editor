package com.lgsim.engine.graphEditor.data.components.util.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LineChartTest {
    public static void main(String[] args) throws Exception {
        //设置数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //setValue(y轴的值,"线条的分类","x轴的值")
        dataset.setValue(8,"a","1");
        dataset.setValue(10,"a","2");
        dataset.setValue(3,"a","3");
        dataset.setValue(5,"a","4");
        dataset.setValue(9,"a","5");
        dataset.setValue(2,"a","6");
        dataset.setValue(7,"a","7");
        //获得JFreeChart对象
        /*JFreeChart chart=ChartFactory.createLineChart(
                        "",  //图表标题
                        "",  //X轴lable
                        "",  //Y轴lable
                        dataset, //数据集
                        PlotOrientation.VERTICAL,//图表放置模式水平/垂直
                        true, //显示lable
                        false,  //显示提示
                        false //显示urls
                );*/

        JFreeChart chart = ChartFactory.createLineChart("test","X","Y",dataset,PlotOrientation.VERTICAL,true,false,false);
        //使用输出流输出图表
        try {
            OutputStream os = new FileOutputStream("C:\\Users\\admin\\Desktop\\test\\test.jpg");
            ChartUtilities.writeChartAsJPEG(os,chart,500,500);
            os.close();
        }catch (IOException e) {
            e.getStackTrace();
        }

    }
}
