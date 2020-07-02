package assignment;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *      HuffmanAlgorithm Assignment
 *      Name: Killian Callaghan
 *      Student Number: 18332783
 */

public class HuffmanAlgorithm {

    //Hashmap to store character prefixes
    private static Map<Character, String> charPrefixHashMap = new HashMap<>();
    //Tree nodes to store characters and their frequency
    static HuffmanNode root;


    /**
     * The main method contains the code for;
     * User input,
     * Selecting files,
     * Reading files,
     * Writing encoded and decoded Strings to files,
     * Calling encode and decode methods.
     * @param args
     */
    public static void main(String[] args) {

        //String stores user input
        String userInputSting = new String();
        //stores the inputted file's content
        String input = "";
        //Boolean to prevent user from decoding before encoding
        boolean canDecodeFile = false;


        label:
        while(!userInputSting.equals("0")) {

            System.out.println("Please enter '-' to encode\nPlease enter '+' to decode\nPlease enter 0 to end the programme");
            Scanner userInput = new Scanner(System.in);
            userInputSting = userInput.nextLine();
            System.out.println("You entered: " + userInputSting);

            switch (userInputSting) {
                case "-":
                    //Encodes


                    //Reading the file
                    try {
                        System.out.println("Please select a file via 'JFileChooser'");
                        JFileChooser ChooseFileToEncode = new JFileChooser();
                        ChooseFileToEncode.setCurrentDirectory(new File(System.getProperty("user.home")));
                        if(ChooseFileToEncode.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            File f = ChooseFileToEncode.getSelectedFile();
                            // File f = new File("C:\\Users\\killi\\OneDrive\\Documents\\GitHub\\algorithm-portfolio-20290-Killian-Callaghan\\src\\assignment\\mobydick.txt");


                            Scanner in = new Scanner(f);
                            //Reading the file
                            while (in.hasNext()) {
                                input += in.nextLine();
                            }
                            //System.out.println("Original Text = " + input);
                            System.out.println("ENCODING");

                            long encodingSpeed = System.nanoTime();
                            //Encoding the read file
                            String output = encode(input);
                            in.close();

                            long elapsedEncodingSpeed = System.nanoTime() - encodingSpeed;
                            System.out.println("the time taken to encode in nano seconds: " + elapsedEncodingSpeed+"\n");

                            System.out.println("Please choose the folder to save of your encoded output");

                            ChooseFileToEncode.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            if(ChooseFileToEncode.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                                //Storing the user chosen directory
                                String encodedOutputDirectory = ChooseFileToEncode.getSelectedFile() + "\\\\EncodedOutput.txt";
                                System.out.println("Saving encoded file");
                                //Writing the encoded message to a file named "EncodedOutput"
                                try {
                                    File outputFile = new File(encodedOutputDirectory);
                                    PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile));

                                    if (!outputFile.exists()) {
                                        outputFile.createNewFile();
                                    }
                                    //Printing the encoded string to the file
                                    printWriter.println(output);
                                    printWriter.close();


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                //Set to true now that a huffman tree has been built which can be used in the decoding process
                                canDecodeFile = true;
                            }
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    break;
                case "+":
                    //Decodes existing decoded message


                    if (canDecodeFile) {


                        System.out.println("Please select a file via 'JFileChooser'");
                        JFileChooser ChooseFileToDecode = new JFileChooser();
                        ChooseFileToDecode.setCurrentDirectory(new File(System.getProperty("user.home")));
                        if(ChooseFileToDecode.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                            //Reading the encoded file
                            try {

                                File f = ChooseFileToDecode.getSelectedFile();

                                String encodedFileString = "";
                                Scanner in = new Scanner(f);
                                while (in.hasNext()) {
                                    encodedFileString += in.nextLine();
                                }

                                System.out.println("DECODING");

                                long decodingSpeed = System.nanoTime();

                                //Decoding the read file
                                String output = decode(encodedFileString);

                                long elapsedDecodingSpeed = System.nanoTime() - decodingSpeed;
                                System.out.println("the time taken to decode in nano seconds: " + elapsedDecodingSpeed +"\n");


                                ChooseFileToDecode.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                if(ChooseFileToDecode.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                                    String decodedOutputDirectory = ChooseFileToDecode.getSelectedFile() + "\\\\DecodedOutput.txt";

                                    System.out.println("Saving decoded file");
                                    //Writing the decoded message to a file named "DecodedOuput"
                                    try {
                                        File outputFile = new File(decodedOutputDirectory);
                                        PrintWriter printWriter = new PrintWriter(new FileOutputStream(decodedOutputDirectory));
                                        if (!outputFile.exists()) {
                                            outputFile.createNewFile();
                                        }

                                        printWriter.print(output);
                                        printWriter.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        System.out.println("The Huffman Tree for this file does not exist, you must encode the message first");
                    }
                    break;
                case "0":
                    System.out.println("Ending program");
                    break label;
                default:
                    System.out.println("Please enter a valid command");
                    break;
            }
        }


        /* Testing the methods


        String inputTest = "test test test";

        System.out.println("Original Text = " + inputTest);
        String output2 = encode(inputTest);

        //ENCODE
        try{
            File outputFile = new File("C:\\Users\\killi\\OneDrive\\Documents\\GitHub\\algorithm-portfolio-20290-Killian-Callaghan\\src\\assignment\\OutputTest.txt");
            //PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile));
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(output2);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            File outputFile = new File("C:\\Users\\killi\\OneDrive\\Documents\\GitHub\\algorithm-portfolio-20290-Killian-Callaghan\\src\\assignment\\OutputTest.txt");
            //PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile));
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(outputFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(output2);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



       // decode(s);

        //DECODE

 */

        /*
            //System.out.println("Original Text = " + input);
            //Decoding the read file
           // String output = decode(input);

            String readTest = new String();
            //Writing the decoded message to a file named "DecodedOuput"

            try {
                File outputFile = new File("C:\\Users\\killi\\OneDrive\\Documents\\GitHub\\algorithm-portfolio-20290-Killian-Callaghan\\src\\assignment\\OutputTest.txt");
                //PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile));
                System.out.println("THE FILE:" + outputFile);
                Scanner in = new Scanner(outputFile);
                while (in.hasNext()) {
                    readTest += in.next();
                }
                in.close();

                String encodeTest = decode(readTest);

                try{ File DecodeOutputFile = new File("C:\\Users\\killi\\OneDrive\\Documents\\GitHub\\algorithm-portfolio-20290-Killian-Callaghan\\src\\assignment\\DecodeOutputTest.txt");
                    //PrintWriter printWriter = new PrintWriter(new FileOutputStream(outputFile));
                    if(!outputFile.exists()){
                        outputFile.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(DecodeOutputFile);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.print(encodeTest);
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

         */






    }


    /**
     * Compresses a String using the Huffman Compression
     * @param input The String inputted from a file that is to be compressed
     * @return The encoded/compressed String
     */
    private static String encode(String input) {

        //Creating a new HashMap
        Map<Character, Integer> freq = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {

            if (!freq.containsKey(input.charAt(i))) {
                freq.put(input.charAt(i), 0);
            }

            freq.put(input.charAt(i), freq.get(input.charAt(i)) + 1);
        }

        //Printing the character frequency map
       // System.out.println("Character Frequency Map = " + freq);

        //Build the tree
        root = buildTree(freq);
        //Create the character prefix map
        setPrefixCodes(root, new StringBuilder());

        //Printing the character prefix map
        //System.out.println("Character Prefix Map = " + charPrefixHashMap);

        StringBuilder s = new StringBuilder();
        //Building the encoded message
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            s.append(charPrefixHashMap.get(c));
        }

        //Returns the encoded message
        return s.toString();
    }

    /**
     * Decodes an encoded Huffman String
     * @param s The encoded String
     */
    private static String decode(String s) {

        //String to hold decoded message
        StringBuilder DecodedMessage = new StringBuilder();

        //Reference to Huffman tree
        HuffmanNode temp = root;

        //Encoded message before being decoded
        //System.out.println("Encoded: " + s);

        //Loops through encoded string
        for (int i = 0; i < s.length(); i++) {

            //j is set to the binary value at a specific index
            int j = Integer.parseInt(String.valueOf(s.charAt(i)));

            //If 0, move down to the left in the tree
            if (j == 0) {

                temp = temp.left;

                //If the node is external, you have reached the character
                if (temp.left == null && temp.right == null) {

                    //Adds the character to the String
                    DecodedMessage.append(temp.data);

                    //Returns to the start of the tree
                    temp = root;
                }
            }

            //If 1, move down to the right in the tree
            if (j == 1) {

                temp = temp.right;

                //If the node is external, you have reached the character
                if (temp.left == null && temp.right == null) {

                    //Adds the character to the String
                    DecodedMessage.append(temp.data);

                    //Returns to the start of the tree
                    temp = root;
                }
            }
        }
        //System.out.println("Decoded string is " + DecodedMessage.toString());
        return DecodedMessage.toString();

    }


    /**
     *  Creating the HashMap to store the prefixes for each character in the input String
     * @param node The Huffman Tree Nodes
     * @param prefix An empty StringBuilder to store the prefix
     */
    private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {

            //Base case
            if (node.left == null && node.right == null) {

                charPrefixHashMap.put(node.data, prefix.toString());

            } else { //Recursive case

                //Left child node
                prefix.append('0');
                //Recursive call, moves down to the left node
                setPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                //Right child node
                prefix.append('1');
                //Recursive call, moves down to the right node
                setPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    /**
     * Builds the tree to store the prefix code for encoding
     * @param freq The nodes
     * @return Returns the last remaining element which is removed
     */
    private static HuffmanNode buildTree(Map<Character, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        Set<Character> keySet = freq.keySet();

        //Creating the nodes
        for (Character c : keySet) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = c;
            huffmanNode.frequency = freq.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;

            //Inserts elements into the priorityQueue
            priorityQueue.offer(huffmanNode);

        }

        //assert priorityQueue.size() > 0;

        //Adds the elements from the priorityQueue to the Huffman Nodes
        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();

            priorityQueue.poll();

            HuffmanNode y = priorityQueue.peek();

            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '-';
            sum.left = x;
            sum.right = y;
            root = sum;
            priorityQueue.offer(sum);
        }

        //Returns the last remaining element which is removed
        return priorityQueue.poll();

    }


}

/**
 * Huffman Node subclass
 */
class HuffmanNode implements Comparable<HuffmanNode> {

    //Number of times a character appears in input string
    int frequency;
    //The character stored in the node
    char data;
    //References to child nodes
    HuffmanNode left, right;

    /**
     * The difference between two nodes
     * @param node The node you want to compare to
     * @return The difference between nodes
     */
    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }
}
