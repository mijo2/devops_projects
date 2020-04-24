import java.util.ArrayList;

class CharacterPrime{
    private String[][] char_primes = new String[26][2];

    public static int[] sieveOfEratosthenes(int n, int n_primes) 
    { 
        boolean prime[] = new boolean[n+1]; 
        for(int i=0;i<n;i++) 
            prime[i] = true; 
          
        for(int p = 2; p*p <=n; p++) 
        { 
            if(prime[p] == true) 
            { 
                for(int i = p*2; i <= n; i += p) 
                    prime[i] = false; 
            } 
        } 
          
        int[] primes = new int[n_primes];
        int counter = 0;
        for(int i = 2; i <= n; i++) 
        { 
            if(prime[i] == true) 
                primes[counter++] = i;
            if(counter == n_primes)
                break;
        } 
        return primes;
    } 

    public static String[][] building_character_primes(){
        String[][] arr = new String[26][2];
        int[] primes = sieveOfEratosthenes(1000, 26);
        for(int i = 0; i < 26; i++){ 
            String[] temp = new String[2];
            char ch = (char)((int) 'A' + i);
            temp[0] = Character.toString(ch);
            temp[1] = String.valueOf(primes[i]);
            arr[i] = temp;
        }
        return arr;
    }

    CharacterPrime(){
        this.char_primes = building_character_primes();
    }

    public void character_to_primes(String str){
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            int index = (int) ch - 'A';
            if(index < 0 || index >= 26){
                System.out.println("Incorrect input!");
                return;
            }
            int output = Integer.parseInt(this.char_primes[index][1]);
            System.out.printf("%s", Integer.toHexString(output));
            if(i != str.length() - 1)
                System.out.printf(", ");
        }
    }

}