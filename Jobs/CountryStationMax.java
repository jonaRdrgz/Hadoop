package countryStationMax;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class CountryStationMax extends Configured implements Tool
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
         String stationAverageTemperature = "EMPTY";
         String stationTemperatureAverageMax = "EMPTY";
         String stationTemperatureAverageMin = "EMPTY";
         String stationRainPrecipitation = "EMPTY";
         String stationWindSpeed = "EMPTY";
         String stationDaysRain = "EMPTY";
         String stationDaysSnow = "EMPTY";
         String stationDaysStorm = "EMPTY";
         String stationFoggyDays = "EMPTY";
         String stationDaysTornado = "EMPTY";
         String stationHailDay = "EMPTY";

         for (Text val : values)
         {
            String [] str = val.toString().split(";");
            String stationStr = str[2];
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
                  stationAverageTemperature = stationStr;
                  maxAverageTemperature = Float.parseFloat(maxAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (maxTemperatureAverageMax<Float.parseFloat(maxTemperatureAverageMaxAux)){
                  stationTemperatureAverageMax = stationStr;
                  maxTemperatureAverageMax = Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }

            if(!maxTemperatureAverageMinAux.equals("-")){
               if (maxTemperatureAverageMin<Float.parseFloat(maxTemperatureAverageMinAux)){
                  stationTemperatureAverageMin = stationStr;
                  maxTemperatureAverageMin = Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation<Float.parseFloat(rainPrecipitationAux)){
                  stationRainPrecipitation = stationStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed<Float.parseFloat(windSpeedAux)){
                  stationWindSpeed = stationStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain<Float.parseFloat(daysRainAux)){
                  stationDaysRain = stationStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow<Float.parseFloat(daysSnowAux)){
                  stationDaysSnow = stationStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm<Float.parseFloat(daysStormAux)){
                  stationDaysStorm = stationStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays<Float.parseFloat(foggyDaysAux)){
                  stationFoggyDays = stationStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado<Float.parseFloat(daysTornadoAux)){
                  stationDaysTornado = stationStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail<Float.parseFloat(daysHailAux)){
                  stationHailDay = stationStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = stationAverageTemperature + ";" + maxAverageTemperature + "\t"+ stationTemperatureAverageMax + ";" + maxTemperatureAverageMax+"\t"+
                  stationTemperatureAverageMin  + ";" + maxTemperatureAverageMin + "\t" + stationRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
                  stationWindSpeed              +  ";" + windSpeed                + "\t" + stationDaysRain           + ";" + daysRain                +"\t" +
                  stationDaysSnow               + ";" + daysSnow                 + "\t" + stationDaysStorm          + ";" + daysStorm               +"\t" +
                  stationFoggyDays              + ";" + foggyDays                +"\t"  + stationDaysTornado        + ";" + daysTornado             +"\t" +
                  stationHailDay                + ";" + daysHail;
         context.write(new Text(key), new Text(value));
      }
   }
   

   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "CountryStationMax");
      job.setJarByClass(CountryStationMax.class);
      
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
      int res = ToolRunner.run(new Configuration(), new CountryStationMax(),ar);
      System.exit(0);
   }
}