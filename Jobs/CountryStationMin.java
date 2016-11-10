package countryStationMin;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class CountryStationMin extends Configured implements Tool
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
         float minAverageTemperature = 999999;
         float minTemperatureAverageMax = 999999;
         float minTemperatureAverageMin = 999999;
         float rainPrecipitation = 999999;
         float windSpeed= 999999;
         float daysRain = 999999;
         float daysSnow = 999999;
         float daysStorm = 999999;
         float foggyDays = 999999;
         float daysTornado = 999999;
         float daysHail = 999999;
         
         int MISSING =  999999;

         // Station for each  value
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
            String minAverageTemperatureAux = str[4];
            String minTemperatureAverageMaxAux = str[5];
            String minTemperatureAverageMinAux = str[6];
            String rainPrecipitationAux = str[7];
            String windSpeedAux = str[8];
         	String daysRainAux = str[9];
         	String daysSnowAux = str[10];
         	String daysStormAux = str[11];
         	String foggyDaysAux = str[12];
         	String daysTornadoAux = str[13];
         	String daysHailAux = str[14];

            // Average temperature
            if(!minAverageTemperatureAux.equals("-")){
               if (minAverageTemperature>Float.parseFloat(minAverageTemperatureAux)){
                  stationAverageTemperature = stationStr;
                  minAverageTemperature = Float.parseFloat(minAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!minTemperatureAverageMaxAux.equals("-")){
               if (minTemperatureAverageMax>Float.parseFloat(minTemperatureAverageMaxAux)){
                  stationTemperatureAverageMax = stationStr;
                  minTemperatureAverageMax = Float.parseFloat(minTemperatureAverageMaxAux);
               }
            }

            if(!minTemperatureAverageMinAux.equals("-")){
               if (minTemperatureAverageMin>Float.parseFloat(minTemperatureAverageMinAux)){
                  stationTemperatureAverageMin = stationStr;
                  minTemperatureAverageMin = Float.parseFloat(minTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation>Float.parseFloat(rainPrecipitationAux)){
                  stationRainPrecipitation = stationStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed>Float.parseFloat(windSpeedAux)){
                  stationWindSpeed = stationStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain>Float.parseFloat(daysRainAux)){
                  stationDaysRain = stationStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow>Float.parseFloat(daysSnowAux)){
                  stationDaysSnow = stationStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm>Float.parseFloat(daysStormAux)){
                  stationDaysStorm = stationStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays>Float.parseFloat(foggyDaysAux)){
                  stationFoggyDays = stationStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado>Float.parseFloat(daysTornadoAux)){
                  stationDaysTornado = stationStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail>Float.parseFloat(daysHailAux)){
                  stationHailDay = stationStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = stationAverageTemperature + ";" + minAverageTemperature + "\t"+ stationTemperatureAverageMax + ";" + minTemperatureAverageMax+"\t"+
                  stationTemperatureAverageMin  + ";" + minTemperatureAverageMin + "\t" + stationRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
                  stationWindSpeed              +  ";" + windSpeed                + "\t" + stationDaysRain           + ";" + daysRain                +"\t" +
                  stationDaysSnow               + ";" + daysSnow                 + "\t" + stationDaysStorm          + ";" + daysStorm               +"\t" +
                  stationFoggyDays              + ";" + foggyDays                +"\t"  + stationDaysTornado        + ";" + daysTornado             +"\t" +
                  stationHailDay                + ";" + daysHail;
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
      
      Job job = new Job(conf, "CountryStationMin");
      job.setJarByClass(CountryStationMin.class);
      
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
      int res = ToolRunner.run(new Configuration(), new CountryStationMin(),ar);
      System.exit(0);
   }
}