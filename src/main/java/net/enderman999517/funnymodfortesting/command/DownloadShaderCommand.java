package net.enderman999517.funnymodfortesting.command;

import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.irisshaders.iris.Iris;
import net.minecraft.text.Text;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

public class DownloadShaderCommand {
    private static final int TIMEOUT = 10000; //timeout in millis
    public static final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
    private static final CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

    public static int downloadShader(FabricClientCommandSource source, String url) throws IOException, InterruptedException, TimeoutException {
        Path shaderpacksFolder = Iris.getShaderpacksDirectory();

        //cleans up url and gets info
        url = url.replace("modrinth.com/shader/", "");
        if (url.contains("https://")) {
            url = url.replace("https://", "");
        }
        String name = StringUtils.substringBefore(url, "/version");
        String version = StringUtils.substringAfter(url, "/version/");


        //creates and goes to the api url
        String apiUrl = "https://api.modrinth.com/v2/project/" + name + "/version/" + version;
        HttpGet apiRequest = new HttpGet(apiUrl);
        apiRequest.setConfig(requestConfig);
        HttpResponse apiResponse = client.execute(apiRequest);
        String apiResponseBody = new BasicResponseHandler().handleResponse(apiResponse);
        if (apiResponse.getStatusLine().getStatusCode() != 200 || apiRequest.isAborted()) {
            source.sendError(Text.translatable("commands.funnymodfortesting.dlshader.error"));
            return 0;
        }

        //gets and goes to the cdn url
        String cdnUrl = JsonParser.parseString(apiResponseBody)
                .getAsJsonObject().getAsJsonArray("files").get(
                        //gets the last object in the list in case of updates or weirdness
                        JsonParser.parseString(apiResponseBody).getAsJsonObject().getAsJsonArray("files").size() - 1)
                .getAsJsonObject().get("url").getAsString();

        String jsonName = JsonParser.parseString(apiResponseBody)
                .getAsJsonObject().get("name").getAsString();

        HttpGet cdnRequest = new HttpGet(cdnUrl);
        cdnRequest.setConfig(requestConfig);
        String path = (shaderpacksFolder.resolve(jsonName + ".zip")).toString();
        File file = new File(path);
        if (!file.exists()) {
            //downloads and writes the file if no errors are present
            HttpResponse cdnResponse = client.execute(cdnRequest);
            HttpEntity entity = cdnResponse.getEntity();
            if (cdnResponse.getStatusLine().getStatusCode() != 200 || apiRequest.isAborted() || entity == null) {
                source.sendError(Text.translatable("commands.funnymodfortesting.dlshader.error"));
                return 0;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            entity.writeTo(fileOutputStream);
            fileOutputStream.close();
            source.sendFeedback(Text.translatable("commands.funnymodfortesting.dlshader.success"));
        } else {
            source.sendFeedback(Text.translatable("commands.funnymodfortesting.dlshader.not_uniqe", jsonName));
        }
        return 1;
    }

}
