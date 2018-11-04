package com.park.textformatting.pedro;

import org.w3c.dom.Text;

public class Main {

    public static void main(String[] args) {

//        // In Class Example...
//        String text = "Why do ghosts make great cheerleaders? Because they have a lot of spirit.";
//        text = "Why do ghosts make great cheerleaders? Because they have a lot of spirit.";
//        System.out.println(TextFormatUtils.format(text, 18));

        // Required text to format...
        String paragraph = "Below is the text from the front of a Mother's day card that I was given back in 1997. " +
                "It is hanging on my door. It has a picture of a woman wearing an apron in her kitchen in what looks like something from the 40s. " +
                "I thought this would make something more interesting than random text to use for this homework. Here is the text. \"Here's Mother. " +
                "She is in the kitchen making supper. Before that she was in the bathroom scrubbing soap scum off the shower. " +
                "This morning she did 12 loads of laundry and developed a computer program that calculates optimum carpool routes. Clever, clever Mother!\"";
        int[] widths = {30, 50, 70};

        for(Integer i : widths) {
            System.out.println("Solution when width = " + i + " is:");
            System.out.println(TextFormatUtils.format(paragraph, i));
        }

    }
}
