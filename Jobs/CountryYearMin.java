package countryYearMin;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class CountryYearMin extends Configured implements Tool
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
                  yearAverageTemperature = yearStr;
                  minAverageTemperature = Float.parseFloat(minAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!minTemperatureAverageMaxAux.equals("-")){
               if (minTemperatureAverageMax>Float.parseFloat(minTemperatureAverageMaxAux)){
                  yearTemperatureAverageMax = yearStr;
                  minTemperatureAverageMax = Float.parseFloat(minTemperatureAverageMaxAux);
               }
            }

            if(!minTemperatureAverageMinAux.equals("-")){
               if (minTemperatureAverageMin>Float.parseFloat(minTemperatureAverageMinAux)){
                  yearTemperatureAverageMin = yearStr;
                  minTemperatureAverageMin = Float.parseFloat(minTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation>Float.parseFloat(rainPrecipitationAux)){
                  yearRainPrecipitation = yearStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed>Float.parseFloat(windSpeedAux)){
                  yearWindSpeed = yearStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain>Float.parseFloat(daysRainAux)){
                  yearDaysRain = yearStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow>Float.parseFloat(daysSnowAux)){
                  yearDaysSnow = yearStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm>Float.parseFloat(daysStormAux)){
                  yearDaysStorm = yearStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays>Float.parseFloat(foggyDaysAux)){
                  yearFoggyDays = yearStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado>Float.parseFloat(daysTornadoAux)){
                  yearDaysTornado = yearStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail>Float.parseFloat(daysHailAux)){
                  yearHailDay = yearStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = yearAverageTemperature + ";" + minAverageTemperature + "\t"+ yearTemperatureAverageMax + ";" + minTemperatureAverageMax+"\t"+
                  yearTemperatureAverageMin  + ";" + minTemperatureAverageMin + "\t" + yearRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
                  yearWindSpeed              +  ";" + windSpeed                + "\t" + yearDaysRain           + ";" + daysRain                +"\t" +
                  yearDaysSnow               + ";" + daysSnow                 + "\t" + yearDaysStorm          + ";" + daysStorm               +"\t" +
                  yearFoggyDays              + ";" + foggyDays                +"\t"  + yearDaysTornado        + ";" + daysTornado             +"\t" +
                  yearHailDay                + ";" + daysHail;
         context.write(new Text(key), new Text(value));
      }
   }
   
   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "countryYearMax");
      job.setJarByClass(CountryYearMin.class);
      
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
      int res = ToolRunner.run(new Configuration(), new CountryYearMin(),ar);
      System.exit(0);
   }
}