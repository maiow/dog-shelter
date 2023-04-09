package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Px
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.redpine.home.Data
import com.redpine.home.HomeBaseFragment
import com.redpine.home.Image
import com.redpine.home.R
import com.redpine.home.databinding.FragmentPetsCardBinding
import com.redpine.home.presentation.home.delegate.HomeAdapter

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }

    private lateinit var adapter: CarouselAdapter
    private lateinit var layoutManager: LayoutManager
    private val args by navArgs<PetsCardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
        setShareButton(args.dogId)
        setCuratorButton(args.dogId)

        val images: ArrayList<Image> = ArrayList(Data.images)

        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        adapter = CarouselAdapter(images)

       // PagerSnapHelper().attachToRecyclerView(binding.dogPhoto)

        binding.dogPhoto.layoutManager = layoutManager
            binding.dogPhoto.adapter = adapter

        val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
        binding.dogPhoto.addItemDecoration(LinearHorizontalSpacingDecoration(spacing))

     /*   with(binding.dogPhoto) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = CarouselAdapter(images)
            Log.i("RED", "adapter is $adapter")

            //val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            //addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
        }*/


    }

    private fun setCloseButton() = binding.backButton.setOnClickListener {
        findNavController().popBackStack()
    }


    private fun setShareButton(dogId: Int) = binding.shareButton.setOnClickListener {
        shareLinkOnDog(viewModel.getDogLink(dogId))
    }


    private fun setCuratorButton(dogId: Int) = binding.curatorButton.setOnClickListener {
        //callCurator(viewModel.getCuratorNumber(dogId))
        sendWhatsAppMessageToCurator(viewModel.getCuratorNumber(dogId), viewModel.getDogName(dogId))
    }


    private fun shareLinkOnDog(link: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://priut-ks.ru/tproduct/$link")
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
    }

    private fun callCurator(phone: String) =
        context?.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))


    private fun sendWhatsAppMessageToCurator(phone: String, dogName: String) {
        val message = "Добрый день! Пишу по поводу собаки $dogName из Красной Сосны"
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phone&text=$message")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}

class LinearHorizontalSpacingDecoration(@Px private val innerSpacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
         super.getItemOffsets(outRect, view, parent, state)


        val itemPosition = parent.getChildAdapterPosition(view)
        Log.i("RED", "innerSpacing = $innerSpacing")

        outRect.left = if (itemPosition == 0) 0 else innerSpacing / 2
        outRect.right = if (itemPosition == state.itemCount - 1) 0 else innerSpacing / 2

        Log.i("RED", "leftRect = ${outRect.left}")

//
//        val spacingPixelSize: Int =
//            parent.context.resources.getDimensionPixelSize(R.dimen.carousel_spacing)
//
//            when (itemPosition) {
//                0 ->
//                    outRect.set(getOffsetPixelSize(parent, view), 0, spacingPixelSize / 2, 0)
//                parent.adapter!!.itemCount - 1 ->
//                    outRect.set(spacingPixelSize / 2, 0, getOffsetPixelSize(parent, view), 0)
//                else ->
//                    outRect.set(spacingPixelSize / 2, 0, spacingPixelSize / 2, 0)
    }
}

private fun getOffsetPixelSize(parent: RecyclerView, view: View): Int {
    val orientationHelper = OrientationHelper.createHorizontalHelper(parent.layoutManager)
    return (orientationHelper.totalSpace - view.layoutParams.width) / 2
}


internal class CarouselAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<CarouselAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ImageView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT
            )
        })
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        val image = images[position]

        Glide.with(vh.imageView).load(image.url).into(vh.imageView)
    }

    override fun getItemCount(): Int = images.size

    class VH(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}
