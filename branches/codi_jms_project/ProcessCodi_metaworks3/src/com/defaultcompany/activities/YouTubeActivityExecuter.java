package com.defaultcompany.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.uengine.contexts.FileContext;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.util.UEngineUtil;

import com.google.gdata.client.media.ResumableGDataFileUploader;
import com.google.gdata.client.uploader.ProgressListener;
import com.google.gdata.client.uploader.ResumableHttpFileUploader;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.mediarss.MediaCategory;
import com.google.gdata.data.media.mediarss.MediaDescription;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaTitle;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;

public class YouTubeActivityExecuter {

    public static final String RESUMABLE_UPLOAD_URL = "http://uploads.gdata.youtube.com/resumable/feeds/api/users/default/uploads";
    private static final int PROGRESS_UPDATE_INTERVAL = 1000;
    private static final int DEFAULT_CHUNK_SIZE = 10000000;
    PrintStream output;
    static Map<String, String> videoFormatMap;
    static {
        videoFormatMap = new HashMap<String, String>();
        
        videoFormatMap.put("WMV", "audio/x-ms-wmv");
        videoFormatMap.put("3GP", "video/3gpp");
        videoFormatMap.put("AVI", "video/avi");
        videoFormatMap.put("MOV", "video/quicktime");
        videoFormatMap.put("MPEG", "video/mpeg");
        videoFormatMap.put("FLV", "video/x-flv");
        videoFormatMap.put("SWF", "application/x-shockwave-flash");
        videoFormatMap.put("MKV", "video/x-matroska");
        videoFormatMap.put("MP4", "video/mp4");
    }
    
    private class FileUploadProgressListener implements ProgressListener {
        public synchronized void progressChanged(ResumableHttpFileUploader uploader) {
            switch (uploader.getUploadState()) {
            case COMPLETE:
                output.println("Upload Completed");
                break;
            case CLIENT_ERROR:
                output.println("Upload Failed");
                break;
            case IN_PROGRESS:
                output.println(String.format("%3.0f", uploader.getProgress() * 100) + "%");
                break;
            case NOT_STARTED:
                output.println("Upload Not Started");
                break;
            }
        }
    }

    public YouTubeActivityExecuter(PrintStream out) {
        this.output = out;
    }

    public void execute(ProcessInstance instance, YouTubeActivity activity) throws Exception {
        
//      YouTubeAuth auth = activity.getAuth();
        RoleMapping auth = activity.getAuth().getMapping(instance);
        auth.fill(instance);
        
        YouTubeMedia media = activity.getMedia();
        
//      String username = auth.getUserName();
//      String password = auth.getPassword();
//      String developerKey = auth.getDeveloperKey();
        
        String username = auth.getExtendedProperty("youtube_username");
        String password = auth.getExtendedProperty("youtube_password");
        String developerKey = auth.getExtendedProperty("youtube_developerKey");

        YouTubeService service = new YouTubeService(activity.evaluateContent(instance, activity.getApplicationName()).toString(), developerKey);
        service.setUserCredentials(username, password);
        String fileSystemDir = "D:\\codi-was8080_Live\\bin\\uengine\\definition\\";
        //String fileSystemDir = "D:\\uengine_standalone\\was\\bin\\uengine\\definition\\";
        String lastChar = fileSystemDir.substring(fileSystemDir.length() - 1, fileSystemDir.length());
        
        if (!"/".equals(lastChar) && !"\\".equals(lastChar)) {
            fileSystemDir += File.separatorChar;
        }
//      File videoFile =    ((FileContext) instance.get("", activity.getVideoFile().getName())).getFile();
        String path = ((FileContext) instance.get("", activity.getVideoFile().getName())).getFile().getPath();
        File videoFile = new File(fileSystemDir + path);
        if (!videoFile.exists()) {
            throw new FileNotFoundException("that video doesn't exist.");
        }

        String videoFormat = videoFile.getName().substring(videoFile.getName().lastIndexOf(".") + 1, videoFile.getName().length());
        MediaFileSource ms = new MediaFileSource(videoFile, videoFormatMap.get(videoFormat.toUpperCase()));

        VideoEntry newEntry = new VideoEntry();
        YouTubeMediaGroup mg = newEntry.getOrCreateMediaGroup();
        
        mg.addCategory(new MediaCategory(YouTubeNamespace.CATEGORY_SCHEME, activity.evaluateContent(instance, media.getCategory()).toString()));
        
        mg.setTitle(new MediaTitle());
        mg.getTitle().setPlainTextContent(activity.evaluateContent(instance, media.getTitle()).toString());
        
        if (UEngineUtil.isNotEmpty(media.getKeyWords())) {
            String[] keyWords = activity.evaluateContent(instance, media.getKeyWords()).toString().split(",");
            for (int i = 0; i < keyWords.length; i++) {
                keyWords[i] = keyWords[i].trim();
            }
            mg.setKeywords(new MediaKeywords());
            mg.getKeywords().addKeywords(Arrays.asList(keyWords));
        }
        
        mg.setDescription(new MediaDescription());
        mg.getDescription().setPlainTextContent(activity.evaluateContent(instance, media.getDescription()).toString());

        FileUploadProgressListener listener = new FileUploadProgressListener();
        ResumableGDataFileUploader uploader = new ResumableGDataFileUploader.Builder(service, new URL(RESUMABLE_UPLOAD_URL), ms, newEntry).title(media.getTitle())
                .trackProgress(listener, PROGRESS_UPDATE_INTERVAL).chunkSize(DEFAULT_CHUNK_SIZE).build();

        uploader.start();
        while (!uploader.isDone()) {
            Thread.sleep(PROGRESS_UPDATE_INTERVAL);
        }

        switch (uploader.getUploadState()) {
        case COMPLETE:
            output.println("Uploaded successfully");
            break;
        case CLIENT_ERROR:
            output.println("Upload Failed");
            break;
        default:
            output.println("Unexpected upload status");
            break;
        }
        
    }

}
