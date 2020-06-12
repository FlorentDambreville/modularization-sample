package com.backmarket.core.api.diagnosis

import com.backmarket.entity.Diagnosis
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*


class RemoteDiagnosisTransformerTest {

    private val transformer = RemoteDiagnosisTransformer()

    @Test
    fun transform() {
        // Given
        val screenTest = mock<Diagnosis.ScreenTouchTest> {
            on { nbColumns } doReturn 12
            on { nbRows } doReturn 42
            on { nbCellsFilled } doReturn 0
        }
        val diagnosis = mock<Diagnosis> {
            on { modelName } doReturn "Awesome android phone"
            on { screenTouchTest } doReturn screenTest
            on { startTime } doReturn Date(1585144985730)
            on { endTime } doReturn Date(1585145003220)
        }

        // When
        val remoteDiagnosis = transformer.transform(diagnosis)

        // Then
        assertThat(remoteDiagnosis.diagnosisInfos.modelName).isEqualTo("Awesome android phone")
        assertThat(remoteDiagnosis.screenTouchTest.nbColumns).isEqualTo(12)
        assertThat(remoteDiagnosis.screenTouchTest.nbRows).isEqualTo(42)
        assertThat(remoteDiagnosis.screenTouchTest.nbCellsFilled).isEqualTo(0)
        assertThat(remoteDiagnosis.startTime).isEqualTo(Date(1585144985730))
        assertThat(remoteDiagnosis.endTime).isEqualTo(Date(1585145003220))
    }
}