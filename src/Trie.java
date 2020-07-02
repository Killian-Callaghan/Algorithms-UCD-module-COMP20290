//import Trie.TrieNode;
public class Trie {
    // Alphabet size (# of symbols) we pick 26 for English alphabet
    static final int ALPHABET_SIZE = 26;

    // class for Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isEndOfWord is true if the node represents end of a word i.e. leaf node
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root;

    // If not key present, inserts into trie
    // If the key is prefix of Trie node,
    // marks leaf node
    static void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode current = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (current.children[index] == null)
                current.children[index] = new TrieNode();

            current = current.children[index];
        }

        // mark last node as leaf
        current.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode current = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (current.children[index] == null)
                return false;

            current = current.children[index];
        }

        return (current != null && current.isEndOfWord);
    }

    // Driver
    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt",
                "silver"};

        root = new TrieNode();
        // Construct trie

        for (int i = 0; i < keys.length; i++)
        {
            insert(keys[i]);
        }

        String[] output = {"Not present in the trie", "Present in the trie"};
        // Search for different keys

        if(search("test") == true)
            System.out.println("test: " + output[1]);
        else System.out.println("test: " + output[0]);

        if(search("film") == true)
            System.out.println("film: " + output[1]);
        else System.out.println("film: " + output[0]);

        if(search("bank") == true)
            System.out.println("bank: " + output[1]);
        else System.out.println("bank: " + output[0]);

        if(search("ring") == true)
            System.out.println("ring: " + output[1]);
        else System.out.println("ring: " + output[0]);

    }

}
//end of class