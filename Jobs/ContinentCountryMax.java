package continentCountryMax;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class ContinentCountryMax extends Configured implements Tool
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
               if (maxAverageTemperature<Float.parseFloat(maxAverageTemperatureAux)){
                  countryAverageTemperature = countryStr;
                  maxAverageTemperature = Float.parseFloat(maxAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (maxTemperatureAverageMax<Float.parseFloat(maxTemperatureAverageMaxAux)){
                  countryTemperatureAverageMax = countryStr;
                  maxTemperatureAverageMax = Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }

            if(!maxTemperatureAverageMinAux.equals("-")){
               if (maxTemperatureAverageMin<Float.parseFloat(maxTemperatureAverageMinAux)){
                  countryTemperatureAverageMin = countryStr;
                  maxTemperatureAverageMin = Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation<Float.parseFloat(rainPrecipitationAux)){
                  countryRainPrecipitation = countryStr;
                  rainPrecipitation = Float.parseFloat(rainPrecipitationAux);
               }
            }
            if(!windSpeedAux.equals("-")){
               if (windSpeed<Float.parseFloat(windSpeedAux)){
                  countryWindSpeed = countryStr;
                  windSpeed = Float.parseFloat(windSpeedAux);
               }
            }
            if(!daysRainAux.equals("-")){
               if (daysRain<Float.parseFloat(daysRainAux)){
                  countryDaysRain = countryStr;
                  daysRain = Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow<Float.parseFloat(daysSnowAux)){
                  countryDaysSnow = countryStr;
                  daysSnow = Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm<Float.parseFloat(daysStormAux)){
                  countryDaysStorm = countryStr;
                  daysStorm = Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays<Float.parseFloat(foggyDaysAux)){
                  countryFoggyDays = countryStr;
                  foggyDays = Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado<Float.parseFloat(daysTornadoAux)){
                  countryDaysTornado = countryStr;
                  daysTornado = Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail<Float.parseFloat(daysHailAux)){
                  countryHailDay = countryStr;
                  daysHail = Float.parseFloat(daysHailAux);
               }
            }
         }

         
         String value = countryAverageTemperature + ";" + maxAverageTemperature + "\t"+ countryTemperatureAverageMax + ";" + maxTemperatureAverageMax+"\t"+
         			countryTemperatureAverageMin  + ";" + maxTemperatureAverageMin + "\t" + countryRainPrecipitation  + ";" + rainPrecipitation       + "\t"+
         			countryWindSpeed              +	";" + windSpeed                + "\t" + countryDaysRain           + ";" + daysRain                +"\t" +
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
      
      Job job = new Job(conf, "countryYearMax");
      job.setJarByClass(ContinentCountryMax.class);
      
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
      int res = ToolRunner.run(new Configuration(), new ContinentCountryMax(),ar);
      System.exit(0);
   }
}