package com.example.arbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode

class MainActivity : AppCompatActivity() {

    lateinit var sceneview: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    lateinit var modelNode: ArModelNode


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneview = findViewById(R.id.sceneview)

        placeButton= findViewById(R.id.place)

        placeButton.setOnClickListener{
            placeModel()
        }

        modelNode= ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "models/buku.glb"
            )
            {
                sceneview.planeRenderer.isVisible = true
        }
            onAnchorChanged = {
                placeButton.isGone
            }
        }
sceneview.addChild(modelNode)
    }

    private fun placeModel(){
        modelNode?.anchor()
        sceneview.planeRenderer.isVisible= false
    }
}