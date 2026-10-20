// إعدادات مشروع Firebase (doctor-2e723). نسخة CDN تعمل مباشرة على GitHub Pages دون أدوات بناء.
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
import { getFirestore } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-firestore.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyBryJb9Qa4LcLWF9e_TTVIz3nyTZGfHpQo",
  authDomain: "doctor-2e723.firebaseapp.com",
  projectId: "doctor-2e723",
  storageBucket: "doctor-2e723.firebasestorage.app",
  messagingSenderId: "269131923941",
  appId: "1:269131923941:web:ec6c698b205e010043af0c",
  measurementId: "G-TF1F548RHH"
};

export const FIREBASE_READY = !firebaseConfig.apiKey.startsWith("YOUR");
const app = initializeApp(firebaseConfig);
export const db = getFirestore(app);
export const auth = getAuth(app);
