package org.mule.examples.snaplights;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class ProcessMessage implements Callable {
	
	private final Map<String, Color> COLOUR_MAP;	
	private ObjectMapper mapper;
	
	public ProcessMessage() {
		mapper = new ObjectMapper();
		COLOUR_MAP = loadColourMap();
	}

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		MMSMessage mms = mapper.readValue(eventContext.getMessageAsString(), MMSMessage.class);
		if(mms.getType().equalsIgnoreCase("command_sms")) {
			String color = mms.getMessage().substring(mms.getMessage().indexOf(' ') +1);
			// Did the user send a smiley face? lets give it a color
			if(color.equals(":)")) color = "yellow"; 
			else if(color.equals(":(")) color = "blue";
			else if(color.equals(":*")) color = "pink";
			else if(color.equals(":-)")) color = "yellow";
			else if(color.equals(":'(")) color = "blue";
			else if(color.equals(":-*")) color = "pink";
			else if(color.equals(";-)")) color = "cyan";
			else if(color.equals(";)")) color = "cyan";
			else if(color.equals(">:(")) color = "blue";
			else if(color.equals("<3")) color = "pink";
			
		   if(!color.startsWith("#")) {
		    	 // Convert color name to a HEX code
				Color c = COLOUR_MAP.get(color.toUpperCase());
				if(c==null) throw new Exception("Well done, you beat the system. I don't understand color: " + color);
				String rgb = Integer.toHexString(c.getRGB());
				color = rgb.substring(2, rgb.length());
			} else {
				throw new Exception("We think you sent a smiley but don't recognise it." + color);
			}
			eventContext.getMessage().setInvocationProperty("color", color);
		}else {
			//We're setting the colors using an image, set the content and location on the message
			ImageRef img = mms.getImages()[0];
			eventContext.getMessage().setInvocationProperty("raw_image", img.getImageBytes());
			eventContext.getMessage().setInvocationProperty("image_url", img.getUrl());
		}
		eventContext.getMessage().setInvocationProperty("sender", mms.getMsisdn());
		return mms;
	}
	
	private static Map<String, java.awt.Color> loadColourMap()
    {
        try
        {
            final Map<String, java.awt.Color> colorMap = new HashMap<String, Color>();
            for (final Field f : java.awt.Color.class.getFields())
            {
                if (f.getType() == java.awt.Color.class)
                {
                    final java.awt.Color c = (java.awt.Color) f.get(null);
                    colorMap.put(f.getName().toUpperCase(), c);
                }
            }
            return colorMap;
        }
        catch (final IllegalAccessException iae)
        {
            throw new RuntimeException(iae);
        }
    }
}
