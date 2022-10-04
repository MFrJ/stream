package com.mfrj

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Status
import io.micronaut.http.server.types.files.StreamedFile
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import reactor.core.publisher.Mono
import java.net.URL

@Controller
@ExecuteOn(TaskExecutors.IO)
class MediaController(
) {
    @Status(HttpStatus.PARTIAL_CONTENT)
    @Get("/audio")
    fun getAudio(
       // @Header currentChunk : Long
    ): Mono<StreamedFile> {

        val audioStream = getResourceStream("/audio.mp3")
        return Mono.just(StreamedFile(audioStream, MediaType("audio/mp3")))

    }


    private fun getResourceStream(path: String) =
         object {}.javaClass.getResource(path).openStream()

}
