package com.example.mynotifactiondemo.common.mapping.profiles

import com.example.mynotifactiondemo.common.mapping.MappingProfileInterface
import com.example.mynotifactiondemo.data.api.dto.MyTransportationResponseDto
import com.example.mynotifactiondemo.ui.main.cargoes.own.details.MyTransportationDetailsModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MyTransportationResponseDtoToMyTransportationDetailsModelMappingProfile
    :
    MappingProfileInterface<MyTransportationResponseDto, MyTransportationDetailsModel> {
    override fun map(source: MyTransportationResponseDto): MyTransportationDetailsModel {
        return MyTransportationDetailsModel(
            orderNumber = "Заявка №${source.number}",
            orderDate = "от ${Format.Date(source.forceDate)}",
            contractNumberAndDate = "к договору №${source.contract.number} от ${Format.Date(source.contract.signingDate)}",
            orderStatusText = source.status.toStatusText(),
            orderStatusTextColor = source.status.toStatusColor(),
            customerBankDetails = "${source.customer.name} ИНН${source.customer.tin}\nКПП ${source.customer.trrc}",
            carrierBankDetails = "${source.carrier.name} ИНН${source.carrier.tin}\nКПП ${source.carrier.trrc}",
            cargoType = source.info.cargoType,
            loadingType = source.info.loadingType,
            trailerVolume = "${source.info.trailerVolume} м³",
            trailerType = source.info.trailerType,
            carryingCapacity = "${source.info.carryingCapacity} тонн",
            costWithoutVat = Format.Currency(source.defaultPayment.cost),
            paymentDueDate = "Оплата не позднее ${source.defaultPayment.daysOffset} рабочих дней с даты получения документов, подтверждающих перевозку",
            additionalRequirements = source.info.details
        )
    }


}