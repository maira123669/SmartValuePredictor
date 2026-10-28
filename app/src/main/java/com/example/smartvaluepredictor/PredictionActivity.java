package com.example.smartvaluepredictor;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class PredictionActivity extends AppCompatActivity {

    private EditText hoursInput;
    private Button predictButton;
    private TextView resultText;

    // Runs the trained TensorFlow Lite model on the device.
    private Interpreter interpreter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        hoursInput = findViewById(R.id.hoursInput);
        predictButton = findViewById(R.id.predictButton);
        resultText = findViewById(R.id.resultText);

        try {
            // Load the model stored in app/src/main/assets/.
            interpreter = new Interpreter(loadModelFile());
        } catch (IOException | RuntimeException e) {
            Toast.makeText(
                    this,
                    "Could not load AI model: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();

            predictButton.setEnabled(false);
            return;
        }

        predictButton.setOnClickListener(v -> predictScore());
    }

    private void predictScore() {
        String text = hoursInput.getText().toString().trim();

        // Check for empty input before converting it to a number.
        if (text.isEmpty()) {
            hoursInput.setError("Please enter study hours");
            return;
        }

        final float hours;

        try {
            hours = Float.parseFloat(text);
        } catch (NumberFormatException e) {
            hoursInput.setError("Enter a valid number");
            return;
        }

        if (Float.isNaN(hours) || Float.isInfinite(hours)
                || hours < 1 || hours > 10) {
            hoursInput.setError("Enter a value from 1 to 10");
            return;
        }

        try {
            // Model input shape: one float value.
            float[][] input = {{hours}};

            // Model output shape: one float value.
            float[][] output = new float[1][1];

            // Perform on-device inference.
            interpreter.run(input, output);

            float score = output[0][0];

            // Display the model's actual output.
            resultText.setText(
                    String.format(
                            java.util.Locale.US,
                            "Estimated score\\n%.2f / 100",
                            score
                    )
            );

        } catch (RuntimeException e) {
            Toast.makeText(
                    this,
                    "Prediction failed: " + e.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    // Maps the .tflite file from the app's assets folder into memory.
    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor descriptor =
                getAssets().openFd("score_model.tflite");

        try (FileInputStream inputStream =
                     new FileInputStream(descriptor.getFileDescriptor());
             FileChannel channel = inputStream.getChannel()) {

            return channel.map(
                    FileChannel.MapMode.READ_ONLY,
                    descriptor.getStartOffset(),
                    descriptor.getLength()
            );
        } finally {
            descriptor.close();
        }
    }

    @Override
    protected void onDestroy() {
        // Release native model resources when the screen closes.
        if (interpreter != null) {
            interpreter.close();
            interpreter = null;
        }

        super.onDestroy();
    }
}