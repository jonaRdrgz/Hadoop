package continentCountryMin;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class ContinentCountryMin extends Configured implements Tool
{
   //Map class
   
   public static class MapClass extends Mapper <LongWritable,Text,Text,Text>
   {
      public void map(LongWritable key, Text value, Context context)
      {
         try{
            String[] str = value.toString().split(";");
            if(str.length==15){
            	String continent = str[0];
            	context.write(new Text(continent), new Text(value));
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

         // Year for each  value
         String countryAverageTemperature = "EMPTY";
         String countryTemperatureAverageMax = "EMPTY";
         String countryTemperatureAverageMin = "EMPTY";
         String countryRainPrecipitation = "EMPTY";
         String countryWindSpeed = "EMPTY";
         String countryDaysRain = "EMPTY";
         String countryDaysSnow = "EMPTY";
         String countryDaysStorm = "EMPTY";
         String countryFoggyDays = "EMPTY";
         String countryDaysTornado = "EMPTY";
         String countryHailDay = "EMPTY";

         for (Text val : values)
         {
            String [] str = val.toString().split(";");
            String countryStr = str[1];
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
               if (minAverageTemperature>Float.parseFloat(maxAverageTemperatureAux)){
                  countryAverageTemperature = countryStr;
                  minAverageTemperature = Float.parseFloat(maxAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (minTemperatureAverageMax>Float.parseFloat(maxTemperatureAverageMaxAux)){
                  countryTemperatureAverageMax = countryStr;
                  minTemperatureAverageMax = Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }

            if(!maxTemperatureAverageMinAux.equals("-")){
               if (minTemperatureAverageMin>Float.parseFloat(maxTemperatureAverageMinAux)){
                  countryTemperatureAverageMin = str[1];
                  minTemperatureAverageMin = Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation>Float.parseFloat(rainPrecipitationAux)){
                  countryRainPrecipitation = countryStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed>Float.parseFloat(windSpeedAux)){
                  countryWindSpeed = countryStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain>Float.parseFloat(daysRainAux)){
                  countryDaysRain = countryStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow>Float.parseFloat(daysSnowAux)){
                  countryDaysSnow = countryStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm>Float.parseFloat(daysStormAux)){
                  countryDaysStorm = countryStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays>Float.parseFloat(foggyDaysAux)){
                  countryFoggyDays = countryStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado>Float.parseFloat(daysTornadoAux)){
                  countryDaysTornado = countryStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail>Float.parseFloat(daysHailAux)){
                  countryHailDay = countryStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = countryAverageTemperature + ";" + minAverageTemperature + "\t"+ countryTemperatureAverageMax + ";" + minTemperatureAverageMax+"\t"+
                  countryTemperatureAverageMin  + ";" + minTemperatureAverageMin + "\t" + countryRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
                  countryWindSpeed              +  ";" + windSpeed                + "\t" + countryDaysRain           + ";" + daysRain                +"\t" +
                  countryDaysSnow               + ";" + daysSnow                 + "\t" + countryDaysStorm          + ";" + daysStorm               +"\t" +
                  countryFoggyDays              + ";" + foggyDays                +"\t"  + countryDaysTornado        + ";" + daysTornado             +"\t" +
                  countryHailDay                + ";" + daysHail;
         context.write(new Text(key), new Text(value));

      }
   }
   
   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "countryYearMin");
      job.setJarByClass(ContinentCountryMin.class);
      
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
      int res = ToolRunner.run(new Configuration(), new ContinentCountryMin(),ar);
      System.exit(0);
   }
}