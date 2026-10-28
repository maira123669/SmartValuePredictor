# StudyScore AI — Android App Using TensorFlow Lite

An AI-based Android mini application developed for **Assignment No. 2: Integrating ML/DL Models into Android with TensorFlow Lite**.

The application uses a machine/deep learning regression model converted into TensorFlow Lite format (`.tflite`) to estimate an exam score based on the number of study hours entered by the user. Model inference runs entirely and directly on the Android device.

> **Disclaimer:** This is an educational demonstration. The model uses artificial example data and is not validated for predicting real exam performance.

---

## 🚀 Project Objectives

* Understand how machine learning models are integrated into Android applications.
* Train a simple regression model using TensorFlow.
* Convert the trained model into TensorFlow Lite format.
* Perform on-device inference using the TensorFlow Lite Interpreter.
* Develop a user-friendly Android application using Java and XML.
* Understand an existing Android TensorFlow Lite project structure.

---

## ✨ Application Features

* Login screen with basic input validation.
* User input for study hours between **1 and 10**.
* On-device AI score estimation.
* Input validation and descriptive error messages.
* Clean results displayed in a simple interface.
* Model inference without requiring a prediction server.
* Educational explanation of the model's limitations.

*Note: The login screen is a demonstration and does not implement real account authentication.*

---

## 🛠️ Technologies Used

* **Android Studio** — Android application development.
* **Java** — Application logic.
* **XML** — User interface design.
* **Python** — Model training.
* **TensorFlow / Keras** — Machine learning model creation.
* **TensorFlow Lite** — Mobile model deployment and inference.
* **Google Colab** — Model training and conversion.
* **Git and GitHub** — Source code management.

---

## 📱 How the Application Works

1. The user opens **StudyScore AI**.
2. The user enters an email address and password on the login screen.
3. The application validates the input and opens the prediction screen.
4. The user enters study hours between **1 and 10**.
5. The app prepares the input as a floating-point tensor.
6. The **TensorFlow Lite Interpreter** runs the model locally.
7. The application displays the model's estimated score.

### Workflow
```text
User Input → Input Validation → TFLite Model → On-Device Inference → Estimated Score
