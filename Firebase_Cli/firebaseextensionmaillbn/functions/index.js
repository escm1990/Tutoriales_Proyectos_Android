const functions = require('firebase-functions');

var admin = require("firebase-admin");

var serviceAccount = require("./las-buenas-nueva.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://las-buenas-nueva-1601087605584.firebaseio.com"
});

exports.obtenerDatos = functions.firestore.document("correos/{correoId}")
.onCreate(async(snapshot,context) => {
    const itemDataSnapshot = await snapshot.ref.get()
    console.log('El email registrado para el mensaje es: '+ itemDataSnapshot.data().email)
    return admin.firestore().collection('mail').add({
        to: [itemDataSnapshot.data().email,'noreply.buenasnuevas@gmail.com'],
        message: {
            subject: 'Dios te bendiga, gracias por comunicarte con nosotros',
            html: '<h3>Hola '+itemDataSnapshot.data().nombre+'</h3><br><br>Gracias por escribirnos. Pedimos a Dios que te bendiga hoy, mañana y siempre. Intentaremos responderte lo antes posible a tu cuenta de correo '+itemDataSnapshot.data().email+'<br><br> Este es un mensaje automático. Favor de no responder a este mensaje.'
        }
    }).then(() => console.log('Mensaje ha sido enviado.'));
});

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//   functions.logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });
