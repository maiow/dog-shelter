package com.redpine.profile.di

import com.redpine.api.Api
import com.redpine.core.domain.AuthDialogPrefs

interface ProfileDependencies {
 val api: Api
 val authDialogPrefs: AuthDialogPrefs
}