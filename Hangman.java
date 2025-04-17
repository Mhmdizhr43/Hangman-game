import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    // Database kata dengan 15 kategori berbeda (total 105 kata)
    private static final String[][][] DATABASE_KATA = {
        // Hewan (7)
        {
            {"GAJAH", "Hewan darat terbesar di dunia", "Hewan"},
            {"KOMODO", "Kadal terbesar yang hidup di Indonesia", "Hewan"},
            {"CENDRAWASIH", "Burung surga dari Papua", "Hewan"},
            {"HARIMAU", "Kucing besar dengan loreng oranye-hitam", "Hewan"},
            {"PENGUIN", "Burung yang tidak bisa terbang tapi pandai berenang", "Hewan"},
            {"KOALA", "Hewan Australia pemakan eucalyptus", "Hewan"},
            {"UBURUBUR", "Hewan laut transparan yang menyengat", "Hewan"}
        },