package org.owasp.wrongsecrets.challenges.docker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.owasp.wrongsecrets.ScoreCard;
import org.owasp.wrongsecrets.challenges.Spoiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ExtendWith(MockitoExtension.class)
class Challenge14Test {

    @Mock
    private ScoreCard scoreCard;

    @Test
    void solveChallenge14() {
        String filePath = getClass().getClassLoader().getResource("alibabacreds.kdbx").getPath();
        var challenge = new Challenge14(scoreCard, "welcome123", "doesnotwork", filePath);
        Assertions.assertThat(challenge.answerCorrect("doesnotwork")).isFalse();
        Assertions.assertThat(challenge.answerCorrect(challenge.spoiler().solution())).isTrue();
    }

    @Test
    void solveChallenge14WithNoFile(){
        var challenge = new Challenge14(scoreCard, "welcome123", "doesnotwork", "nofile is here");
        Assertions.assertThat(challenge.answerCorrect("doesnotwork")).isTrue();
        Assertions.assertThat(challenge.spoiler().solution()).isEqualTo( "doesnotwork");
    }

}
