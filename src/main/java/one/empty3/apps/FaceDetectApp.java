package one.empty3.apps;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.*;
import com.google.api.services.vision.v1.model.Image;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.ImmutableList;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
//import com.google.cloud.vision.v1.AnnotateImageRequest;
//import com.google.cloud.vision.v1.AnnotateImageResponse;
//import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
//import com.google.cloud.vision.v1.Feature;
//import com.google.cloud.vision.v1.Feature.Type;
//import com.google.cloud.vision.v1.Image;
//import com.google.cloud.vision.v1.ImageAnnotatorClient;
//import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.SafeSearchAnnotation;
import com.google.gson.JsonObject;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import one.empty3.feature.jviolajones.Rect;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.function.Consumer;
import javax.imageio.ImageIO;

public class FaceDetectApp {
    private static final String BLURRED_BUCKET_NAME = "output-pictures";
    private static Storage storage = StorageOptions.getDefaultInstance().getService();
    private static final String APPLICATION_NAME = "MeshMask";
    private static final int MAX_RESULTS = 10;
    private static String projectId;
    private final Vision vision;

    public FaceDetectApp(Vision visionService) {
        this.vision = visionService;
    }

    /**
     * Connects to the Vision API using Application Default Credentials.
     */
    public static Vision getVisionService() throws IOException, GeneralSecurityException {
        GoogleCredentials credential =
                GoogleCredentials.getApplicationDefault().createScoped(VisionScopes.all());
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        return new Vision.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credential))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Gets up to {@code maxResults} faces for an image stored at {@code path}.
     */
    public List<FaceAnnotation> detectFaces(Path path, int maxResults) throws IOException {
        byte[] data = Files.readAllBytes(path);

        AnnotateImageRequest request =
                new AnnotateImageRequest()
                        .setImage(new Image().encodeContent(data))
                        .setFeatures(
                                ImmutableList.of(
                                        new Feature().setType("FACE_DETECTION").setMaxResults(maxResults)));
        Vision.Images.Annotate annotate =
                vision
                        .images()
                        .annotate(new BatchAnnotateImagesRequest().setRequests(ImmutableList.of(request)));
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotate.setDisableGZipContent(true);

        BatchAnnotateImagesResponse batchResponse = annotate.execute();
        assert batchResponse.getResponses().size() == 1;
        AnnotateImageResponse response = batchResponse.getResponses().get(0);
        if (response.getFaceAnnotations() == null) {
            throw new IOException(
                    response.getError() != null
                            ? response.getError().getMessage()
                            : "Unknown error getting image annotations");
        }
        return response.getFaceAnnotations();
    }

    /**
     * Reads image {@code inputPath} and writes {@code outputPath} with {@code faces} outlined.
     */
    private void writeWithFaces(Path inputPath, Path outputPath, List<FaceAnnotation> faces)
            throws IOException {
        BufferedImage img = ImageIO.read(inputPath.toFile());
        annotateWithFaces(img, faces);
        ImageIO.write(img, "jpg", outputPath.toFile());
    }

    /**
     * Annotates an image {@code img} with a polygon around each face in {@code faces}.
     */
    public void annotateWithFaces(BufferedImage img, List<FaceAnnotation> faces) {
        for (FaceAnnotation face : faces) {
            annotateWithFace(img, face);
            writePolygonsData(img, face);
        }
    }

    int landmarkIndex = 0;

    private void writePolygonsData(BufferedImage img, FaceAnnotation face) {

        face.getLandmarks().forEach(new Consumer<Landmark>() {
            @Override
            public void accept(Landmark landmark) {
                System.out.printf("Landmark #%d\n", landmarkIndex);
                System.out.println("TYPE " + landmark.getType());
                System.out.println("POSITION " + landmark.getPosition());
                Iterator<Map.Entry<String, Object>> iterator = landmark.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, Object> next = iterator.next();
                    System.out.printf("Landmark #%d KEY{%s} TYPE {%s}: %s\n" + landmarkIndex, String.valueOf(next.getKey()), String.valueOf(next.getValue().getClass().getCanonicalName()), String.valueOf(next.getValue()));
                }
                landmarkIndex++;
            }
        });
/*
        BoundingPoly bounds = face.getBoundingPoly();
        float rotX = face.getHeadEulerAngleX();  // Head is rotated to the right rotY degrees
        float rotY = face.getHeadEulerAngleY();  // Head is rotated to the right rotY degrees
        float rotZ = face.getHeadEulerAngleZ();  // Head is tilted sideways rotZ degrees

        // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
        // nose available):
        //FaceLandmark leftEar = face.getLandmarks().get(GoogleCloudVisionV1p4beta1FaceAnnotationLandmark.LEFT_EAR);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);


        PointF leftEarPos = null;
        if (leftEar != null) {
            leftEarPos = leftEar.getPosition();
        }
        // If landmark detection was enabled (mouth, ears, eyes, cheeks, and
        // nose available):
        PointF rightEarPos = null;
        FaceLandmark rightEar = face.getLandmark(FaceLandmark.RIGHT_EAR);
        if (rightEar != null) {
            rightEarPos = rightEar.getPosition();
        }

        // If contour detection was enabled:
        List<PointF> leftEyeContour = null;
        try {
            leftEyeContour =
                    face.getContour(FaceContour.LEFT_EYE).getPoints();
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        }
        List<PointF> rightEyeContour = null;
        try {
            // If contour detection was enabled:
            rightEyeContour =
                    face.getContour(FaceContour.RIGHT_EYE).getPoints();
        } catch (NullPointerException ex) {
            //ex.printStackTrace();
        }

        // If classification was enabled:
        float smileProb = 0f;
        if (face.getSmilingProbability() != null) {
            smileProb = face.getSmilingProbability();
        }
        float rightEyeOpenProb = 0f;
        if (face.getRightEyeOpenProbability() != null) {
            rightEyeOpenProb = face.getRightEyeOpenProbability();
        }

        // If face tracking was enabled:
        int id = -1;
        if (face.getTrackingId() != null) {
            id = face.getTrackingId();
        }

        paint.setColor(Color.RED);

        paint.setColor(Color.BLUE);
        if (leftEyeContour != null && leftEyeContour.size() >= 2) {
            for (int i = 0; i < leftEyeContour.size(); i++) {
//                drawLine(coordCanvas(leftEyeContour.get(i)), coordCanvas(leftEyeContour.get((i + 1) % leftEyeContour.size())));
            }
        }
        if (rightEyeContour != null && rightEyeContour.size() >= 2) {
            for (int i = 0; i < rightEyeContour.size(); i++) {
//                drawLine(coordCanvas(rightEyeContour.get(i)), coordCanvas(rightEyeContour.get((i + 1) % rightEyeContour.size())));
            }
        }
        drawFace(face, faceData);
  */
    }

    /**
     * Annotates an image {@code img} with a polygon defined by {@code face}.
     */
    private static void annotateWithFace(BufferedImage img, FaceAnnotation face) {
        Graphics2D gfx = img.createGraphics();
        Polygon poly = new Polygon();
        for (Vertex vertex : face.getFdBoundingPoly().getVertices()) {
            poly.addPoint(vertex.getX(), vertex.getY());
        }
        gfx.setStroke(new BasicStroke(5));
        gfx.setColor(new Color(0x00ff00));
        gfx.draw(poly);
    }

    /**
     * Annotates an image using the Vision API.
     */
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        if (args.length != 2) {
            System.err.println("Usage:");
            System.err.printf(
                    "\tjava %s inputImagePath outputImagePath\n", FaceDetectApp.class.getCanonicalName());
            System.exit(1);
        }
        Path inputPath = Paths.get(args[0]);
        Path outputPath = Paths.get(args[1]);
        if (!outputPath.toString().toLowerCase().endsWith(".jpg")) {
            System.err.println("outputImagePath must have the file extension 'jpg'.");
            System.exit(1);
        }

        FaceDetectApp app = new FaceDetectApp(getVisionService());
        List<FaceAnnotation> faces = app.detectFaces(inputPath, MAX_RESULTS);
        System.out.printf("Found %d face%s\n", faces.size(), faces.size() == 1 ? "" : "s");
        System.out.printf("Writing to file %s\n", outputPath);
        app.writeWithFaces(inputPath, outputPath, faces);

        //sendToBucket(BLURRED_BUCKET_NAME, outputPath);
        uploadFile(outputPath.toFile());
    }

    /*
        public static void sendToBucket(String BLURRED_BUCKET_NAME, Path outputPath) {
            // Upload image to blurred bucket.
            BlobId blurredBlobId = BlobId.of(BLURRED_BUCKET_NAME, outputPath);
            BlobInfo blurredBlobInfo =
                    BlobInfo.newBuilder(blurredBlobId).setContentType(blob.getContentType()).build();
            try {
                byte[] blurredFile = Files.readAllBytes(outputPath);
                Blob blurredBlob = storage.create(blurredBlobInfo, blurredFile);
                System.out.println(
                        String.format("Applied effects image to: gs://%s/%s", BLURRED_BUCKET_NAME, outputPath));
            } catch (Exception e) {
                System.out.println(String.format("Error in upload: %s", e.getMessage()));
            }
        }
    */
    // upload file to GCS
    public static void uploadFile(File filename) throws IOException {
        // Create a new GCS client
        storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        // The blob ID identifies the newly created blob, which consists of a bucket name and an object
        // name
        BlobId blobId = BlobId.of(BLURRED_BUCKET_NAME, filename.getName());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();


        // upload the file and print the status
        storage.createFrom(blobInfo, Paths.get(filename.getAbsolutePath()));
        System.out.println("File " + filename.getAbsolutePath() + " uploaded to bucket " + BLURRED_BUCKET_NAME + " as " + filename);
    }
}