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
        // Buah (7)
        {
            {"DURIAN", "Raja buah dengan bau menyengat", "Buah"},
            {"MANGGA", "Buah tropis dengan rasa manis asam", "Buah"},
            {"RAMBUTAN", "Buah berambut merah dari Asia Tenggara", "Buah"},
            {"KIWI", "Buah kecil berbulu dari Selandia Baru", "Buah"},
            {"STROBERI", "Buah merah berbintik dengan rasa manis-asam", "Buah"},
            {"NANAS", "Buah tropis dengan mata banyak", "Buah"},
            {"ALPUKAT", "Buah lembut tinggi lemak sehat", "Buah"}
        },
        // Negara (7)
        {
            {"INDONESIA", "Negara kepulauan terbesar di dunia", "Negara"},
            {"JEPANG", "Negara matahari terbit dengan bunga sakura", "Negara"},
            {"MESIR", "Negara piramida dan sungai Nil", "Negara"},
            {"BRASIL", "Negara terbesar di Amerika Selatan", "Negara"},
            {"KANADA", "Negara dengan daun maple di bendera", "Negara"},
            {"ITALIA", "Negara berbentuk boot penghasil pizza", "Negara"},
            {"AUSTRALIA", "Negara benua dengan kanguru", "Negara"}
        },
        // Kota (7)
        {
            {"JAKARTA", "Ibukota Indonesia", "Kota"},
            {"PARIS", "Kota mode dan menara Eiffel", "Kota"},
            {"TOKYO", "Ibukota Jepang yang super sibuk", "Kota"},
            {"BALI", "Pulau dewata tujuan wisata", "Kota"},
            {"ROMA", "Kota abadi di Italia", "Kota"},
            {"NEWYORK", "Kota yang tidak pernah tidur di Amerika", "Kota"},
            {"SINGAPURA", "Negara kota di Asia Tenggara", "Kota"}
        },
        // Benda (7)
        {
            {"KIPASANGIN", "Alat pendingin dengan baling-baling", "Benda"},
            {"TELEPON", "Alat komunikasi jarak jauh", "Benda"},
            {"KULKAS", "Alat pendingin makanan", "Benda"},
            {"PISAU", "Alat potong dengan mata tajam", "Benda"},
            {"JAMTANGAN", "Penunjuk waktu di pergelangan", "Benda"},
            {"PENSIL", "Alat tulis dengan isi grafit", "Benda"},
            {"TAS", "Wadah untuk membawa barang", "Benda"}
        },
        // Makanan (7)
        {
            {"NASIGORENG", "Makanan nasi digoreng dengan bumbu", "Makanan"},
            {"SATE", "Daging tusuk dengan bumbu kacang", "Makanan"},
            {"PIZZA", "Roti pipih dengan topping Italia", "Makanan"},
            {"SUSHI", "Makanan Jepang dari nasi dan ikan mentah", "Makanan"},
            {"BAKSO", "Makanan bola daging dengan kuah", "Makanan"},
            {"RENDANG", "Masakan Padang dengan daging lembut", "Makanan"},
            {"TEMPE", "Makanan fermentasi kedelai khas Indonesia", "Makanan"}
        },
        // Profesi (7)
        {
            {"DOKTER", "Tenaga medis yang merawat pasien", "Profesi"},
            {"POLISI", "Penjaga hukum dan ketertiban", "Profesi"},
            {"GURU", "Pendidik generasi muda", "Profesi"},
            {"PROGRAMMER", "Pembuat aplikasi dan software", "Profesi"},
            {"KOKI", "Ahli memasak profesional", "Profesi"},
            {"PILOT", "Pengendali pesawat terbang", "Profesi"},
            {"ARsiteK", "Perancang bangunan", "Profesi"}
        },
        // Olahraga (7)
        {
            {"SEPAKBOLA", "Olahraga dengan bola dan gawang", "Olahraga"},
            {"BULUTANGKIS", "Olahraga dengan raket dan shuttlecock", "Olahraga"},
            {"TENIS", "Olahraga dengan raket dan bola kecil", "Olahraga"},
            {"RENANG", "Olahraga di air", "Olahraga"},
            {"BASKET", "Olahraga dengan ring dan bola besar", "Olahraga"},
            {"VOLI", "Olahraga dengan net dan bola besar", "Olahraga"},
            {"GOLF", "Olahraga dengan stick dan bola kecil", "Olahraga"}
        },
        // Kendaraan (7)
        {
            {"MOBIL", "Kendaraan roda empat", "Kendaraan"},
            {"KERETA", "Kendaraan rel panjang", "Kendaraan"},
            {"PESAWAT", "Kendaraan terbang", "Kendaraan"},
            {"KAPAL", "Kendaraan laut besar", "Kendaraan"},
            {"MOTOR", "Kendaraan roda dua", "Kendaraan"},
            {"HELIKOPTER", "Kendaraan terbang dengan baling-baling", "Kendaraan"},
            {"BECAK", "Kendaraan tradisional roda tiga", "Kendaraan"}
        },
        // Tanaman (7)
        {
            {"MAWAR", "Bunga berduri simbol cinta", "Tanaman"},
            {"ANGGREK", "Bunga eksotis dengan banyak jenis", "Tanaman"},
            {"BAMBU", "Tanaman berongga dengan pertumbuhan cepat", "Tanaman"},
            {"KELAPA", "Tanaman pantai dengan buah berair", "Tanaman"},
            {"PADI", "Tanaman penghasil beras", "Tanaman"},
            {"KAKAO", "Tanaman penghasil cokelat", "Tanaman"},
            {"JATI", "Pohon dengan kayu berkualitas tinggi", "Tanaman"}
        },
        // Film (7)
        {
            {"AVATAR", "Film sci-fi dengan manusia biru", "Film"},
            {"TITANIC", "Film kapal tenggelam percintaan", "Film"},
            {"FROZEN", "Film Disney tentang ratu es", "Film"},
            {"HARRYPOTTER", "Film penyihir dengan tongkat ajaib", "Film"},
            {"JOKER", "Film tentang penjahat DC yang gila", "Film"},
            {"INTERSTELLAR", "Film perjalanan antariksa", "Film"},
            {"SPIDERMAN", "Film superhero dengan laba-laba", "Film"}
        },
        // Tokoh (7)
        {
            {"SUKARNO", "Proklamator Indonesia pertama", "Tokoh"},
            {"EINSTEIN", "Ilmuwan teori relativitas", "Tokoh"},
            {"MICHELANGELO", "Seniman Renaissance pelukis Kapel Sistina", "Tokoh"},
            {"CUTNYAKDIEN", "Pahlawan wanita dari Aceh", "Tokoh"},
            {"BJHABIBIE", "Presiden Indonesia ke-3 ahli pesawat", "Tokoh"},
            {"MARIO", "Karakter game Nintendo perangko", "Tokoh"},
            {"BATMAN", "Pahlawan Gotham dengan kostum kelelawar", "Tokoh"}
        },
        // Bandara (7)
        {
            {"SOEKARNO-HATTA", "Bandara utama di Jakarta", "Bandara"},
            {"CHANGI", "Bandara terbaik di Singapura", "Bandara"},
            {"HEATHROW", "Bandara tersibuk di London", "Bandara"},
            {"JUANDA", "Bandara internasional di Surabaya", "Bandara"},
            {"NGURAHRAI", "Bandara utama di Bali", "Bandara"},
            {"NARITA", "Bandara internasional di Tokyo", "Bandara"},
            {"KUALANAMU", "Bandara utama di Medan", "Bandara"}
        },
        // Planet (7)
        {
            {"BUMI", "Planet tempat kita hidup", "Planet"},
            {"MARS", "Planet merah", "Planet"},
            {"JUPITER", "Planet terbesar di tata surya", "Planet"},
            {"VENUS", "Planet terpanas di tata surya", "Planet"},
            {"SATURNUS", "Planet dengan cincin indah", "Planet"},
            {"MERKURIUS", "Planet terdekat dengan matahari", "Planet"},
            {"NEPTUNUS", "Planet biru terjauh", "Planet"}
        },
        // Aplikasi (7)
        {
            {"WHATSAPP", "Aplikasi pesan instan populer", "Aplikasi"},
            {"TIKTOK", "Aplikasi video pendek viral", "Aplikasi"},
            {"INSTAGRAM", "Aplikasi berbagi foto dan video", "Aplikasi"},
            {"GOOGLE", "Mesin pencari terbesar di dunia", "Aplikasi"},
            {"GRAB", "Aplikasi transportasi dan pengiriman", "Aplikasi"},
            {"SPOTIFY", "Aplikasi streaming musik", "Aplikasi"},
            {"YOUTUBE", "Platform video terbesar di dunia", "Aplikasi"}
        }
    };

    private static final int MAX_TRIES = 4;
    private static final int MAX_HINTS = 2;
    private static int score = 0;
    private static List<String> usedCategories = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("====================================");
        System.out.println("|   SUPER HANGMAN TEBAK KATA 100+     |");
        System.out.println("|   Dengan 15 Kategori Berbeda!    |");
        System.out.println("====================================");
        System.out.print("\nMasukkan nama Anda: ");
        String playerName = scanner.nextLine();
        int correctStreak = 0;

        boolean playAgain = true;
        int gamesPlayed = 0;
        int streak = 0; // Menyimpan jumlah kemenangan berturut-turut
        int extraLife = 0; // Tambahan nyawa dari streak
        
        while (playAgain) {
            if (usedCategories.size() == DATABASE_KATA.length) {
                usedCategories.clear(); // Reset jika semua kategori sudah digunakan
            }
            
            int categoryIndex;
            do {
                categoryIndex = random.nextInt(DATABASE_KATA.length);
            } while (usedCategories.contains(DATABASE_KATA[categoryIndex][0][2]));
            
            usedCategories.add(DATABASE_KATA[categoryIndex][0][2]);
            int wordIndex = random.nextInt(DATABASE_KATA[categoryIndex].length);
            
            String wordToGuess = DATABASE_KATA[categoryIndex][wordIndex][0];
            String hint = DATABASE_KATA[categoryIndex][wordIndex][1];
            String category = DATABASE_KATA[categoryIndex][wordIndex][2];
            
            char[] guessedLetters = new char[wordToGuess.length()];
            Arrays.fill(guessedLetters, '_');
            
            int remainingTries = MAX_TRIES + extraLife;
            extraLife = 0; // nyawa tambahan hanya berlaku 1 kali
            int remainingHints = MAX_HINTS;
            StringBuilder usedLetters = new StringBuilder();
            
            System.out.println("\n=== PERMAINAN KE-" + (++gamesPlayed) + " ===");
            System.out.println("Kategori: " + category);
            System.out.println("Petunjuk: " + hint);
            System.out.println("Panjang kata: " + wordToGuess.length() + " huruf");
            System.out.println("Kesempatan salah: " + remainingTries);
            System.out.println("Bantuan tersedia: " + remainingHints);
            
            while (remainingTries > 0 && !isWordGuessed(guessedLetters)) {
                System.out.println("\nKata: " + displayGuessedLetters(guessedLetters));
                System.out.println("Nyawa: " + "❤".repeat(remainingTries));
                System.out.println("Bantuan: " + remainingHints + " tersisa");
                System.out.println("Huruf digunakan: " + usedLetters);
                
                System.out.print("Masukkan tebakan (1 huruf atau '?' untuk bantuan): ");
                String input = scanner.next().toUpperCase();
                
                if (input.equals("?")) {
                    if (remainingHints > 0) {
                        giveHint(wordToGuess, guessedLetters);
                        remainingHints--;
                        System.out.println("Bantuan digunakan! Satu huruf terungkap.");
                    } else {
                        System.out.println("Anda sudah menggunakan semua bantuan!");
                    }
                    continue;
                }
                
                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    System.out.println("Masukkan 1 huruf atau '?' untuk bantuan!");
                    continue;
                }
                
                char guess = input.charAt(0);
                
                if (usedLetters.indexOf(String.valueOf(guess)) != -1) {
                    System.out.println("Anda sudah menebak huruf '" + guess + "' sebelumnya.");
                    continue;
                }
                
                usedLetters.append(guess).append(" ");
                
                if (wordToGuess.indexOf(guess) != -1) {
                    System.out.println("Tebakan benar!");
                    updateGuessedLetters(wordToGuess, guessedLetters, guess);
                } else {
                    System.out.println("Tebakan salah! Huruf '" + guess + "' tidak ada dalam kata.");
                    remainingTries--;
                    drawHangman(remainingTries);
                }
            }
            
            if (isWordGuessed(guessedLetters)) {
                score += remainingTries * 10;
                correctStreak++;

                if (correctStreak % 3 == 0) {
                    remainingTries++;
                    System.out.println("Bonus: Anda mendapat 1 nyawa tambahan karena menang 3 kali berturut-turut!");
                }

                System.out.println("\n=== SELAMAT " + playerName + "! ANDA MENANG! ===");
                System.out.println("Jawaban: " + wordToGuess);
                System.out.println("Skor Anda: " + score);
            } else {
                correctStreak = 0;
                System.out.println("\n=== GAME OVER ===");
                System.out.println("Jawaban: " + wordToGuess);
                System.out.println("Skor Anda: " + score);
            }
            
            System.out.print("\nMain lagi? (y/n): ");
            String choice = scanner.next().toLowerCase();
            playAgain = choice.equals("y");
        }
        
        System.out.println("\n====================================");
        System.out.println("  TERIMA KASIH " + playerName + " TELAH BERMAIN!");
        System.out.println("  Total Permainan: " + gamesPlayed);
        System.out.println("  Skor Akhir Anda: " + score);
        System.out.println("====================================");
        scanner.close();
    }
    
    // [Method-method pendukung sama dengan sebelumnya...]
    private static void giveHint(String word, char[] guessedLetters) {
        Random random = new Random();
        int hintPos;
        
        do {
            hintPos = random.nextInt(word.length());
        } while (guessedLetters[hintPos] != '_');
        
        guessedLetters[hintPos] = word.charAt(hintPos);
    }
    
    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char c : guessedLetters) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
    
    private static String displayGuessedLetters(char[] guessedLetters) {
        StringBuilder sb = new StringBuilder();
        for (char c : guessedLetters) {
            sb.append(c).append(" ");
        }
        return sb.toString();
    }
    
    private static void updateGuessedLetters(String word, char[] guessedLetters, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = guess;
            }
        }
    }
    
    private static void drawHangman(int remainingTries) {
        System.out.println("\n  -----");
        System.out.println("  |   |");
        
        switch (remainingTries) {
            case 5:
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                break;
            case 4:
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                break;
            case 3:
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                break;
            case 2:
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                break;
            case 1:
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                break;
            case 0:
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                break;
        }
        
        System.out.println("      |");
        System.out.println("========");
    }
}