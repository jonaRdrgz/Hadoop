package moyenneContinent;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;

import org.apache.hadoop.util.*;

public class MoyenneContinent extends Configured implements Tool
{
   //Map class
   
   public static class MapClass extends Mapper <LongWritable,Text,Text,Text>
   {
      public void map(LongWritable key, Text value, Context context)
      {
         try{
            String[] str = value.toString().split(";");
            
            String continent = str[0];
            context.write(new Text(continent), new Text(value));
            
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
      int lenValue = 0;
      String maxAverageTemperatureAux;
      String maxTemperatureAverageMaxAux ;
      String maxTemperatureAverageMinAux;
      String rainPrecipitationAux;
      String windSpeedAux;
      String daysRainAux;
      String daysSnowAux;
      String daysStormAux;
      String foggyDaysAux;
      String daysTornadoAux;
      String daysHailAux;

      public void reduce(Text key, Iterable <Text> values, Context context) throws IOException, InterruptedException
      {
      	// Initial values
         float maxAverageTemperature = 0;
         float maxTemperatureAverageMax = 0;
         float maxTemperatureAverageMin = 0;
         float rainPrecipitation = 0;
         float windSpeed= 0;
         float daysRain = 0;
         float daysSnow = 0;
         float daysStorm = 0;
         float foggyDays = 0;
         float daysTornado = 0;
         float daysHail = 0;
         

         // Counters for Moyyenne
         int counterAverageTemperature = 0;
         int counterTemperatureAverageMax = 0;
         int counterTemperatureAverageMin = 0;
         int counterRainPrecipitation = 0;
         int counterWindSpeed = 0;
         int counterDaysRain = 0;
         int counterDaysSnow = 0;
         int counterDaysStorm = 0;
         int counterFoggyDays = 0;
         int counterDaysTornado = 0;
         int counterHailDay = 0;

         for (Text val : values)
         {
            String [] str = val.toString().split(";");
            this.lenValue = str.length;
            if(lenValue == 15){
               maxAverageTemperatureAux = str[4];
               maxTemperatureAverageMaxAux = str[5];
               maxTemperatureAverageMinAux = str[6];
               rainPrecipitationAux = str[7];
               windSpeedAux = str[8];
               daysRainAux = str[9];
               daysSnowAux = str[10];
               daysStormAux = str[11];
               foggyDaysAux = str[12];
               daysTornadoAux = str[13];
               daysHailAux = str[14];
            }

            else if(lenValue==14){
               maxAverageTemperatureAux = str[3];
               maxTemperatureAverageMaxAux = str[4];
               maxTemperatureAverageMinAux = str[5];
               rainPrecipitationAux = str[6];
               windSpeedAux = str[7];
               daysRainAux = str[8];
               daysSnowAux = str[9];
               daysStormAux = str[10];
               foggyDaysAux = str[11];
               daysTornadoAux = str[12];
               daysHailAux = str[13];
            }
            

            // Average temperature
            if(!maxAverageTemperatureAux.equals("-")){
               if (maxAverageTemperature<Float.parseFloat(maxAverageTemperatureAux)){
                  counterAverageTemperature += 1;
                  maxAverageTemperature += Float.parseFloat(maxAverageTemperatureAux);
               }
            }

            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (maxTemperatureAverageMax<Float.parseFloat(maxTemperatureAverageMaxAux)){
                  counterTemperatureAverageMax += 1;
                  maxTemperatureAverageMax += Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }

            // Anual average minimum temperature
            if(!maxTemperatureAverageMinAux.equals("-")){
               if (maxTemperatureAverageMin<Float.parseFloat(maxTemperatureAverageMinAux)){
                  counterTemperatureAverageMin += 1;
                  maxTemperatureAverageMin += Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }

            // Days precipitation
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation<Float.parseFloat(rainPrecipitationAux)){
                  counterRainPrecipitation += 1;
                  rainPrecipitation += Float.parseFloat(rainPrecipitationAux);
               }
            }

            // Wind Speed 
            if(!windSpeedAux.equals("-")){
               if (windSpeed<Float.parseFloat(windSpeedAux)){
                  counterWindSpeed += 1;
                  windSpeed += Float.parseFloat(windSpeedAux);
               }
            }

            // 
            if(!daysRainAux.equals("-")){
               if (daysRain<Float.parseFloat(daysRainAux)){
                  counterDaysRain += 1;
                  daysRain += Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow<Float.parseFloat(daysSnowAux)){
                  counterDaysSnow += 1;
                  daysSnow += Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm<Float.parseFloat(daysStormAux)){
                  counterDaysStorm += 1;
                  daysStorm += Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays<Float.parseFloat(foggyDaysAux)){
                  counterFoggyDays += 1;
                  foggyDays += Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado<Float.parseFloat(daysTornadoAux)){
                  counterDaysTornado += 1;
                  daysTornado += Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail<Float.parseFloat(daysHailAux)){
                  counterHailDay += 1;
                  daysHail += Float.parseFloat(daysHailAux);
               }
            }
         }

         maxAverageTemperature =  maxAverageTemperature / counterAverageTemperature;
         maxTemperatureAverageMax = maxTemperatureAverageMax / counterTemperatureAverageMax;
         maxTemperatureAverageMin = maxTemperatureAverageMin / counterTemperatureAverageMin;
         rainPrecipitation = rainPrecipitation / counterRainPrecipitation;
         windSpeed = windSpeed / counterWindSpeed;
         daysRain = daysRain / counterDaysRain;
         daysSnow = daysSnow / counterDaysSnow;
         daysStorm = daysStorm / counterDaysStorm;
         foggyDays = foggyDays / counterFoggyDays;
         daysTornado = daysTornado / counterDaysTornado;
         daysHail = daysHail / counterHailDay;


         
         String value = Float.toString(maxAverageTemperature) + "\t"+ Float.toString(maxTemperatureAverageMax) +"\t"+
                  Float.toString(maxTemperatureAverageMin)+"\t"+
         			Float.toString(rainPrecipitation) +"\t"+Float.toString(windSpeed)  +"\t"+ Float.toString(daysRain) +"\t"+
         			Float.toString(daysSnow)  + "\t" + Float.toString(daysStorm) + "\t" + Float.toString(foggyDays) +"\t"+
         			Float.toString(daysTornado) +"\t"+ Float.toString(daysHail);

         context.write(new Text(key), new Text(value));
      }
   }
   
   //Partitioner class
   
   public static class CaderPartitioner extends
   Partitioner < Text, Text >
   {
      int year;
      @Override
      public int getPartition(Text key, Text value, int numReduceTasks)
      {
         String[] str = value.toString().split(";");

         if(str.length == 15){
            year = Integer.parseInt(str[3]);

         }
         else{
            year = Integer.parseInt(str[2]);
         }
         
         
         if(numReduceTasks == 0)
         {
            return 0;
         }
         
         if(year<=1929)
         {
            return 0;
         }
         else if(year>1929 && year<=1939)
         {
            return 1 % numReduceTasks;
         }
         else if(year>1939 && year<=1949)
         {
            return 2 % numReduceTasks;
         }
         else if(year>1949 && year<=1959)
         {
            return 3 % numReduceTasks;
         }
         else if(year>1959 && year<=1969)
         {
            return 4 % numReduceTasks;
         }
         else if(year>1969 && year<=1979)
         {
            return 5 % numReduceTasks;
         }
         else if(year>1979 && year<=1989)
         {
            return 6 % numReduceTasks;
         }
         else if(year>1989 && year<=1999)
         {
            return 7 % numReduceTasks;
         }
         else if(year>1999 && year<=2009)
         {
            return 8 % numReduceTasks;
         }
         else
         {
            return 9 % numReduceTasks;
         }
      }
   }
   
   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "MoyenneContinent");
      job.setJarByClass(MoyenneContinent.class);
      
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
      
      job.setMapperClass(MapClass.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      //set partitioner statement
      
      job.setPartitionerClass(CaderPartitioner.class);
      job.setReducerClass(ReduceClass.class);
      job.setNumReduceTasks(10);
      job.setInputFormatClass(TextInputFormat.class);
      
      job.setOutputFormatClass(TextOutputFormat.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      
      System.exit(job.waitForCompletion(true)? 0 : 1);
      return 0;
   }
   
   public static void main(String ar[]) throws Exception
   {
      int res = ToolRunner.run(new Configuration(), new MoyenneContinent(),ar);
      System.exit(0);
   }
}