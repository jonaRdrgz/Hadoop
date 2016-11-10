package countryYearMax;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class CountryYearMax extends Configured implements Tool
{
   //Map class
   
   public static class MapClass extends Mapper <LongWritable,Text,Text,Text>
   {
      public void map(LongWritable key, Text value, Context context)
      {
         try{
            String[] str = value.toString().split(";");
            if(str.length==15){
            	String country = str[1];
            	context.write(new Text(country), new Text(value));
            }
            
         }
         catch(Exception e)
         {
            System.out.println(e.getMessage());
         }
      }
   } 
   
   //Reducer class
   
   public static class ReduceClass extends Reducer <Text,Text,Text,Text>
   {
      
      public void reduce(Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException
      {
      	// Initial values
         float maxAverageTemperature = -999999;
         float maxTemperatureAverageMax = -999999;
         float maxTemperatureAverageMin = -999999;
         float rainPrecipitation = 0;
         float windSpeed= 0;
         float daysRain = 0;
         float daysSnow = 0;
         float daysStorm = 0;
         float foggyDays = 0;
         float daysTornado = 0;
         float daysHail = 0;
         
         int MISSING =  -999999;

         // Year for each  value
         String yearAverageTemperature = "EMPTY";
         String yearTemperatureAverageMax = "EMPTY";
         String yearTemperatureAverageMin = "EMPTY";
         String yearRainPrecipitation = "EMPTY";
         String yearWindSpeed = "EMPTY";
         String yearDaysRain = "EMPTY";
         String yearDaysSnow = "EMPTY";
         String yearDaysStorm = "EMPTY";
         String yearFoggyDays = "EMPTY";
         String yearDaysTornado = "EMPTY";
         String yearHailDay = "EMPTY";

         for (Text val : values)
         {
            String [] str = val.toString().split(";");
            String yearStr = str[3];
            String maxAverageTemperatureAux = str[4];
            String maxTemperatureAverageMaxAux = str[5];
            String maxTemperatureAverageMinAux = str[6];
            String rainPrecipitationAux = str[7];
            String windSpeedAux = str[8];
            String daysRainAux = str[9];
            String daysSnowAux = str[10];
            String daysStormAux = str[11];
            String foggyDaysAux = str[12];
            String daysTornadoAux = str[13];
            String daysHailAux = str[14];

            // Average temperature
            if(!maxAverageTemperatureAux.equals("-")){
               if (maxAverageTemperature<Float.parseFloat(maxAverageTemperatureAux)){
                  yearAverageTemperature = yearStr;
                  maxAverageTemperature = Float.parseFloat(maxAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (maxTemperatureAverageMax<Float.parseFloat(maxTemperatureAverageMaxAux)){
                  yearTemperatureAverageMax = yearStr;
                  maxTemperatureAverageMax = Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }

            if(!maxTemperatureAverageMinAux.equals("-")){
               if (maxTemperatureAverageMin<Float.parseFloat(maxTemperatureAverageMinAux)){
                  yearTemperatureAverageMin = yearStr;
                  maxTemperatureAverageMin = Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation<Float.parseFloat(rainPrecipitationAux)){
                  yearRainPrecipitation = yearStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed<Float.parseFloat(windSpeedAux)){
                  yearWindSpeed = yearStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain<Float.parseFloat(daysRainAux)){
                  yearDaysRain = yearStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow<Float.parseFloat(daysSnowAux)){
                  yearDaysSnow = yearStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm<Float.parseFloat(daysStormAux)){
                  yearDaysStorm = yearStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays<Float.parseFloat(foggyDaysAux)){
                  yearFoggyDays = yearStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado<Float.parseFloat(daysTornadoAux)){
                  yearDaysTornado = yearStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail<Float.parseFloat(daysHailAux)){
                  yearHailDay = yearStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = yearAverageTemperature + ";" + maxAverageTemperature + "\t"+ yearTemperatureAverageMax + ";" + maxTemperatureAverageMax+"\t"+
                  yearTemperatureAverageMin  + ";" + maxTemperatureAverageMin + "\t" + yearRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
                  yearWindSpeed              +  ";" + windSpeed                + "\t" + yearDaysRain           + ";" + daysRain                +"\t" +
                  yearDaysSnow               + ";" + daysSnow                 + "\t" + yearDaysStorm          + ";" + daysStorm               +"\t" +
                  yearFoggyDays              + ";" + foggyDays                +"\t"  + yearDaysTornado        + ";" + daysTornado             +"\t" +
                  yearHailDay                + ";" + daysHail;
         context.write(new Text(key), new Text(value));
      }
   }
   
   //Partitioner class
   
   public static class CaderPartitioner extends
   Partitioner < Text, Text >
   {
      @Override
      public int getPartition(Text key, Text value, int numReduceTasks)
      {
         String[] str = value.toString().split("\t");
         int age = Integer.parseInt(str[2]);
         
         if(numReduceTasks == 0)
         {
            return 0;
         }
         else{
            return 0;
         }
      }
   }
   
   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "countryYearMax");
      job.setJarByClass(CountryYearMax.class);
      
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
      
      job.setMapperClass(MapClass.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      //set partitioner statement
      
      //job.setPartitionerClass(CaderPartitioner.class);
      job.setReducerClass(ReduceClass.class);
      //job.setNumReduceTasks(0);
      job.setInputFormatClass(TextInputFormat.class);
      
      job.setOutputFormatClass(TextOutputFormat.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      
      System.exit(job.waitForCompletion(true)? 0 : 1);
      return 0;
   }
   
   public static void main(String ar[]) throws Exception
   {
      int res = ToolRunner.run(new Configuration(), new CountryYearMax(),ar);
      System.exit(0);
   }
}