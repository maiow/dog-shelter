package com.redpine.profile.di

interface ProfileDependenciesProvider {
    var dependencies:ProfileDependencies

    companion object: ProfileDependenciesProvider by ProfileStore
}