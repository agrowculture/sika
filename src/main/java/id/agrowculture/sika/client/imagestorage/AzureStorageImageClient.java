package id.agrowculture.sika.client.imagestorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AzureStorageImageClient implements ImageStorageClient {

  private final BlobServiceClient blobServiceClient;

  @Override
  public String uploadImage(String containerName, String originalImageName, InputStream data, long length)
      throws IOException {
    BlobContainerClient blobContainerClient = this.blobServiceClient.getBlobContainerClient(containerName);

    String newImageName = UUID.randomUUID().toString()
        + originalImageName.substring(originalImageName.lastIndexOf('.'));

    BlobClient blobClient = blobContainerClient.getBlobClient(newImageName);

    blobClient.upload(data, length, true);

    return blobClient.getBlobUrl();
  }

}
