# Setting up and Running PHP API with XAMPP on Linux

  ## Prerequisites

- XAMPP installed on your Linux machine
 - PHP file (my_Quiz_api.php) located in /opt/lampp/htdocs/quiz/ folder
  - Database (my_Quiz_db) set up in MySQL

### Step 1: Install XAMPP on Linux

- Download XAMPP from XAMPP Official Website.

- Install XAMPP on your machine.

      sudo tar xvfz xampp-linux-x64-<version>.tar.gz -C /opt
- After installation, you should have XAMPP located at /opt/lampp.

### Step 2: Start XAMPP

- Open a Terminal and run the following command to start XAMPP:

      sudo /opt/lampp/lampp start

- This will start Apache and MySQL, allowing you to run your PHP files locally.

### Step 3: Place Your PHP File in XAMPP's htdocs Folder

- Navigate to /opt/lampp/htdocs/quiz/ folder where your PHP file should reside.

- To place your my_Quiz_api.php file, run:

      sudo cp /path/to/your/my_Quiz_api.php /opt/lampp/htdocs/quiz/

- Replace /path/to/your/my_Quiz_api.php with the actual file path.

### Step 4: Verify Your PHP File is Accessible

- Open your browser and visit:

      http://localhost/quiz/my_Quiz_api.php

- If everything is set up correctly, you should see a JSON response or your PHP API output.

### Step 5: Open and Edit Your PHP File from the Terminal

- To open and edit the my_Quiz_api.php file using the terminal editor nano, run:

      sudo nano /opt/lampp/htdocs/quiz/my_Quiz_api.php

- nano is a simple terminal text editor.
        Use the Arrow Keys to navigate, and make your changes.
        To save the file, press Ctrl + O, then Enter.
        To exit, press Ctrl + X.


### Step 6: Check Your API Response in the Browser

- After editing and saving the file, open your browser and visit:

      http://localhost/quiz/my_Quiz_api.php

    - You should receive a JSON output (if that's what your PHP file is designed to return).

### Step 7: Troubleshooting
- Blank Page Issue

- If you see a blank page:

    - Enable error reporting in the PHP file:

           <?php
            error_reporting(E_ALL);
            ini_set('display_errors', 1);

           // Your database connection and query code here...
              ?>

- If you're still facing issues, check the Apache error logs:

        sudo tail -f /opt/lampp/logs/error_log

- MySQL Database Connection Issue

- Make sure your MySQL database is running and the connection credentials are correct in your PHP file.

- You can check MySQL status by running:

       sudo /opt/lampp/lampp status

### Step 8: Access the PHP API from Android

- To connect your Android app to this PHP API:

   - Use Retrofit to make API requests to http://10.0.2.2/quiz/my_Quiz_api.php (for Android Emulator).

   -  Add INTERNET permission in the AndroidManifest.xml:

           <uses-permission android:name="android.permission.INTERNET"/>

   - Use GsonConverterFactory to parse JSON responses in Retrofit.

#### Example of RetrofitInstance:

     import retrofit2.Retrofit;
     import retrofit2.converter.gson.GsonConverterFactory;

     public class RetrofitInstance {

    // Always use 10.0.2.2 to connect emulator to the host's server
    String baseUrl = "http://10.0.2.2/quiz/";

    // Create and Return a configured Retrofit Instance
    public Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       }
    }

### Step 9: Stop XAMPP

- When you're done, stop XAMPP:

       sudo /opt/lampp/lampp stop
