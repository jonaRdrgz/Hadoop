package topTenMoyenneCountry;


import java.io.*;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;

import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.*;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class TopTenMoyenneCountry extends Configured implements Tool
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
      //Store the top ten
      private TreeMap<Float, String> topTenAverageTemperature = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTemperatureAverageMax = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenTemperatureAverageMin = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenRainPrecipitation = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenWindSpeed = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenDaysRain = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenDaysSnow = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenDaysStorm = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenFoggyDays = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenDaysTornado = new TreeMap<Float, String>();
      private TreeMap<Float, String> topTenDaysHail = new TreeMap<Float, String>();


      private String maxAverageTemperatureAux;
      private String maxTemperatureAverageMaxAux ;
      private String maxTemperatureAverageMinAux;
      private String rainPrecipitationAux;
      private String windSpeedAux;
      private String daysRainAux;
      private String daysSnowAux;
      private String daysStormAux;
      private String foggyDaysAux;
      private String daysTornadoAux;
      private String daysHailAux;
      private int top = 10; 

      @Override
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
            

            // Average temperature
            if(!maxAverageTemperatureAux.equals("-")){
               if (maxAverageTemperature < Float.parseFloat(maxAverageTemperatureAux)){
                  counterAverageTemperature++;
                  maxAverageTemperature += Float.parseFloat(maxAverageTemperatureAux);
               }
            }
            
            // Annual average maximun temperature
            if(!maxTemperatureAverageMaxAux.equals("-")){
               if (maxTemperatureAverageMax<Float.parseFloat(maxTemperatureAverageMaxAux)){
                  counterTemperatureAverageMax++;
                  maxTemperatureAverageMax += Float.parseFloat(maxTemperatureAverageMaxAux);
               }
            }
            
            // Anual average minimum temperature
            if(!maxTemperatureAverageMinAux.equals("-")){
               if (maxTemperatureAverageMin<Float.parseFloat(maxTemperatureAverageMinAux)){
                  counterTemperatureAverageMin++;
                  maxTemperatureAverageMin += Float.parseFloat(maxTemperatureAverageMinAux);
               }
            }

            // Days precipitation
            if(!rainPrecipitationAux.equals("-")){
               if (rainPrecipitation<Float.parseFloat(rainPrecipitationAux)){
                  counterRainPrecipitation++;
                  rainPrecipitation += Float.parseFloat(rainPrecipitationAux);
               }
            }

            // Wind Speed 
            if(!windSpeedAux.equals("-")){
               if (windSpeed<Float.parseFloat(windSpeedAux)){
                  counterWindSpeed++;
                  windSpeed += Float.parseFloat(windSpeedAux);
               }
            }

            // 
            if(!daysRainAux.equals("-")){
               if (daysRain<Float.parseFloat(daysRainAux)){
                  counterDaysRain++;
                  daysRain += Float.parseFloat(daysRainAux);
               }
            }
            if(!daysSnowAux.equals("-")){
               if (daysSnow<Float.parseFloat(daysSnowAux)){
                  counterDaysSnow++;
                  daysSnow += Float.parseFloat(daysSnowAux);
               }
            }
            if(!daysStormAux.equals("-")){
               if (daysStorm<Float.parseFloat(daysStormAux)){
                  counterDaysStorm++;
                  daysStorm += Float.parseFloat(daysStormAux);
               }
            }
            if(!foggyDaysAux.equals("-")){
               if (foggyDays<Float.parseFloat(foggyDaysAux)){
                  counterFoggyDays++;
                  foggyDays += Float.parseFloat(foggyDaysAux);
               }
            }
            if(!daysTornadoAux.equals("-")){
               if (daysTornado<Float.parseFloat(daysTornadoAux)){
                  counterDaysTornado++;
                  daysTornado += Float.parseFloat(daysTornadoAux);
               }
            }
            if(!daysHailAux.equals("-")){
               if (daysHail<Float.parseFloat(daysHailAux)){
                  counterHailDay++;
                  daysHail += Float.parseFloat(daysHailAux);
               }
            }
         }

         //
         if(counterAverageTemperature != 0){
            insertMoyenne(maxAverageTemperature, counterAverageTemperature,topTenAverageTemperature, key);
         }

         if(counterTemperatureAverageMax != 0){
            maxTemperatureAverageMax =  maxTemperatureAverageMax / counterTemperatureAverageMax;
            topTenTemperatureAverageMax.put(maxTemperatureAverageMax, key + "");
            if(topTenTemperatureAverageMax.size()>top){
               topTenTemperatureAverageMax.remove(topTenTemperatureAverageMax.firstKey());
            }
         }

         if(counterTemperatureAverageMin != 0){
            maxTemperatureAverageMin =  maxTemperatureAverageMin / counterTemperatureAverageMin;
            topTenTemperatureAverageMin.put(maxTemperatureAverageMin, key + "");
            if(topTenTemperatureAverageMin.size()>top){
               topTenTemperatureAverageMin.remove(topTenTemperatureAverageMin.firstKey());
            }
         }

         if(counterRainPrecipitation != 0){
            rainPrecipitation =  rainPrecipitation / counterRainPrecipitation;
            topTenRainPrecipitation.put(rainPrecipitation, key + "");
            if(topTenRainPrecipitation.size()>top){
               topTenRainPrecipitation.remove(topTenRainPrecipitation.firstKey());
            }
         }

         if(counterWindSpeed != 0){
            windSpeed =  windSpeed / counterWindSpeed;
            topTenWindSpeed.put(windSpeed, key + "");
            if(topTenWindSpeed.size()>top){
               topTenWindSpeed.remove(topTenWindSpeed.firstKey());
            }
         }

         if(counterDaysRain != 0){
            daysRain =  daysRain / counterDaysRain;
            topTenDaysRain.put(daysRain, key + "");
            if(topTenDaysRain.size()>top){
               topTenDaysRain.remove(topTenDaysRain.firstKey());
            }
         }

         if(counterDaysSnow != 0){
            daysSnow =  daysSnow / counterDaysSnow;
            topTenDaysSnow.put(daysSnow, key + "");
            if(topTenDaysSnow.size()>top){
               topTenDaysSnow.remove(topTenDaysSnow.firstKey());
            }
         }

         if(counterDaysStorm != 0){
            daysStorm =  daysStorm / counterDaysStorm;
            topTenDaysStorm.put(daysStorm, key + "");
            if(topTenDaysStorm.size()>top){
               topTenDaysStorm.remove(topTenDaysStorm.firstKey());
            }
         }
         if(counterFoggyDays != 0){
            foggyDays =  foggyDays / counterFoggyDays;
            topTenFoggyDays.put(foggyDays, key + "");
            if(topTenFoggyDays.size()>top){
               topTenFoggyDays.remove(topTenFoggyDays.firstKey());
            }
         }
         if(counterDaysTornado != 0){
            daysTornado =  daysTornado / counterDaysTornado;
            topTenDaysTornado.put(daysTornado, key + "");
            if(topTenDaysTornado.size()>top){
               topTenDaysTornado.remove(topTenDaysTornado.firstKey());
            }
         }
         if(counterHailDay != 0){
            daysHail =  daysHail / counterHailDay;
            topTenDaysHail.put(daysHail, key + "");
            if(topTenDaysHail.size()>top){
               topTenDaysHail.remove(topTenDaysHail.firstKey());
            }
         }
      }

      @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            String topCountryAverageTemperature = getTopFromMap(topTenAverageTemperature);
            String topCountryTemperatureAverageMax = getTopFromMap(topTenTemperatureAverageMax);
            String topCountryTemperatureAverageMin = getTopFromMap(topTenTemperatureAverageMin);
            String topCountryRainPrecipitation = getTopFromMap(topTenRainPrecipitation);
            String topCountryWindSpeed = getTopFromMap(topTenWindSpeed);
            String topCountryDaysRain = getTopFromMap(topTenDaysRain);
            String topCountryDaysSnow = getTopFromMap(topTenDaysSnow);
            String topCountryDaysStorm = getTopFromMap(topTenDaysStorm);
            String topCountryFoggyDays = getTopFromMap(topTenFoggyDays);
            String topCountryDaysTornado = getTopFromMap(topTenDaysTornado);
            String topCountryDaysHail = getTopFromMap(topTenDaysHail);


            String key = "TopTenCountry";
            String value = topCountryAverageTemperature +"\t"+ topCountryTemperatureAverageMax + "\t"+ topCountryTemperatureAverageMin +
                        "\t" + topCountryRainPrecipitation +"\t" + topCountryWindSpeed +"\t" + topCountryDaysRain + "\t" + topCountryDaysSnow + 
                        "\t" + topCountryDaysStorm + "\t" + topCountryFoggyDays + "\t" + topCountryDaysTornado + "\t" + topCountryDaysHail;
            context.write(new Text(key), new Text(value));
      }
      private String getTopFromMap(TreeMap<Float, String> mapTree){
            Set set = mapTree.entrySet();
            Iterator iterator = set.iterator();
            String topCountry ="";
            while(iterator.hasNext()) {
               Map.Entry mentry = (Map.Entry)iterator.next();
               String key = Float.toString((float)mentry.getKey());
               topCountry = (String)mentry.getValue() + ";"+ key + "," + topCountry;
            }
            return topCountry;
      }

      private void insertMoyenne(float totalStored, int counter, TreeMap<Float, String> mapTree, Text key){
         float moyenne  =  totalStored / counter;
         //Save into TreeMap
         mapTree.put(moyenne, key + "");
         if(mapTree.size()>this.top){
            mapTree.remove(mapTree.firstKey());
         }
      }
   }
   
   @Override
   public int run(String[] arg) throws Exception
   {
      Configuration conf = getConf();
      
      Job job = new Job(conf, "TopTenMoyenneCountry");
      job.setJarByClass(TopTenMoyenneCountry.class);
      
      FileInputFormat.setInputPaths(job, new Path(arg[0]));
      FileOutputFormat.setOutputPath(job,new Path(arg[1]));
      
      job.setMapperClass(MapClass.class);
      
      job.setMapOutputKeyClass(Text.class);
      job.setMapOutputValueClass(Text.class);
      
      //set partitioner statement
      
      //job.setPartitionerClass(CaderPartitioner.class);
      job.setReducerClass(ReduceClass.class);
      //job.setNumReduceTasks(10);
      job.setInputFormatClass(TextInputFormat.class);
      
      job.setOutputFormatClass(TextOutputFormat.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(Text.class);
      
      System.exit(job.waitForCompletion(true)? 0 : 1);
      return 0;
   }
   
   public static void main(String ar[]) throws Exception
   {
      int res = ToolRunner.run(new Configuration(), new TopTenMoyenneCountry(),ar);
      System.exit(0);
   }
}